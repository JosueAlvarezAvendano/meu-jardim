import { useState, useEffect } from "react";
import styles from "./FormPlanta.module.css";

function FormPlanta({ onPlantaCadastrada, plantaEditando, setPlantaEditando }) {

    const [form, setForm] = useState({
        nome: "",
        especie: "",
        tipo: "",
        frequenciaRega: "",
        nivelLuz: "",
        descricao: ""
    });

    const [status, setStatus] = useState(null);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (plantaEditando) {
            setForm({
                nome: plantaEditando.nome,
                especie: plantaEditando.especie,
                tipo: plantaEditando.tipo,
                frequenciaRega: plantaEditando.frequenciaRega,
                nivelLuz: plantaEditando.nivelLuz,
                descricao: plantaEditando.descricao || ""
            });
        } else {
            setForm({ nome: "", especie: "", tipo: "", frequenciaRega: "", nivelLuz: "", descricao: "" });
        }
    }, [plantaEditando]);

    function handleChange(e) {
        setForm({ ...form, [e.target.name]: e.target.value });
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setLoading(true);
        setStatus(null);

        // Se tem plantaEditando → PUT, senão → POST
        const url = plantaEditando
            ? `http://localhost:8080/plantas/${plantaEditando.id}`
            : "http://localhost:8080/plantas";

        const method = plantaEditando ? "PUT" : "POST";

        try {
            const resposta = await fetch(url, {
                method: method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(form)
            });

            if (resposta.status === 201 || resposta.status === 200) {
                setStatus("sucesso");
                setForm({ nome: "", especie: "", tipo: "", frequenciaRega: "", nivelLuz: "", descricao: "" });
                setPlantaEditando(null); // volta para modo cadastro
                onPlantaCadastrada();
            } else {
                setStatus("erro");
            }
        } catch (error) {
            setStatus("erro");
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className={styles.container}>
            {/* Título muda dependendo do modo */}
            <h2 className={styles.titulo}>
                {plantaEditando ? "Editar Planta" : "Cadastrar Planta"}
            </h2>

            <form onSubmit={handleSubmit} className={styles.form}>
                <div className={styles.campo}>
                    <label>Nome</label>
                    <input name="nome" value={form.nome} onChange={handleChange} placeholder="Ex: Samambaia" required />
                </div>

                <div className={styles.campo}>
                    <label>Espécie</label>
                    <input name="especie" value={form.especie} onChange={handleChange} placeholder="Ex: Nephrolepis exaltata" required />
                </div>

                <div className={styles.campo}>
                    <label>Tipo</label>
                    <select name="tipo" value={form.tipo} onChange={handleChange} required>
                        <option value="">Selecione</option>
                        <option value="Flor">Flor</option>
                        <option value="Erva">Erva</option>
                        <option value="Suculenta">Suculenta</option>
                        <option value="Árvore">Árvore</option>
                    </select>
                </div>

                <div className={styles.campo}>
                    <label>Frequência de Rega</label>
                    <select name="frequenciaRega" value={form.frequenciaRega} onChange={handleChange} required>
                        <option value="">Selecione</option>
                        <option value="Diária">Diária</option>
                        <option value="Semanal">Semanal</option>
                        <option value="Quinzenal">Quinzenal</option>
                    </select>
                </div>

                <div className={styles.campo}>
                    <label>Nível de Luz</label>
                    <select name="nivelLuz" value={form.nivelLuz} onChange={handleChange} required>
                        <option value="">Selecione</option>
                        <option value="Pleno sol">Pleno sol</option>
                        <option value="Meia sombra">Meia sombra</option>
                        <option value="Sombra">Sombra</option>
                    </select>
                </div>

                <div className={styles.campo}>
                    <label>Descrição</label>
                    <textarea name="descricao" value={form.descricao} onChange={handleChange} placeholder="Observações sobre a planta..." />
                </div>

                <button type="submit" className={styles.botao} disabled={loading}>
                    {loading ? "Salvando..." : plantaEditando ? "Salvar alterações" : "Cadastrar"}
                </button>

                {/* Botão de cancelar edição */}
                {plantaEditando && (
                    <button
                        type="button"
                        className={styles.botaoCancelar}
                        onClick={() => setPlantaEditando(null)}
                    >
                        Cancelar
                    </button>
                )}

                {status === "sucesso" && <p className={styles.sucesso}>
                    {plantaEditando ? "Planta atualizada com sucesso!" : "Planta cadastrada com sucesso!"}
                </p>}
                {status === "erro" && <p className={styles.erro}>Erro ao salvar. Verifique os campos.</p>}
            </form>
        </div>
    );
}

export default FormPlanta;