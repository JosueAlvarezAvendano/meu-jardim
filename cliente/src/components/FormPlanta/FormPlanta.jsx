import { useState } from "react";
import styles from "./FormPlanta.module.css";

function FormPlanta({ onPlantaCadastrada }) {

    const [form, setForm] = useState({
        nome: "",
        especie: "",
        tipo: "",
        frequenciaRega: "",
        nivelLuz: "",
        descricao: ""
    });

    const [status, setStatus] = useState(null); // null, "sucesso", "erro"
    const [loading, setLoading] = useState(false);

    function handleChange(e) {
        setForm({ ...form, [e.target.name]: e.target.value });
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setLoading(true);
        setStatus(null);

        try {
            const resposta = await fetch("http://localhost:8080/plantas", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(form)
            });

            if (resposta.status === 201) {
                setStatus("sucesso");
                setForm({ nome: "", especie: "", tipo: "", frequenciaRega: "", nivelLuz: "", descricao: "" });
                onPlantaCadastrada(); // avisa o App para atualizar a lista
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
            <h2 className={styles.titulo}>Cadastrar Planta</h2>

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
                    {loading ? "Cadastrando..." : "Cadastrar"}
                </button>

                {status === "sucesso" && <p className={styles.sucesso}>Planta cadastrada com sucesso!</p>}
                {status === "erro" && <p className={styles.erro}>Erro ao cadastrar. Verifique os campos.</p>}

            </form>
        </div>
    );
}

export default FormPlanta;