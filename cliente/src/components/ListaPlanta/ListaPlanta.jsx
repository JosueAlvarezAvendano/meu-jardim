import styles from "./ListaPlanta.module.css";

function ListaPlanta({ plantas, loading, onPlantaDeletada }) {

    async function deletarPlanta(id, nome) {
        const confirmado = confirm(`Tem certeza que deseja excluir "${nome}"?`);

        if (!confirmado) return;

        try {
            const resposta = await fetch(`http://localhost:8080/plantas/${id}`, {
                method: "DELETE"
            });

            if (resposta.status === 204) {
                onPlantaDeletada(); // avisa o App para atualizar a lista
            } else {
                alert("Erro ao excluir a planta.");
            }
        } catch (error) {
            alert("Erro ao conectar com a API.");
        }
    }

    if (loading) {
        return <p className={styles.mensagem}>Carregando plantas...</p>;
    }

    if (plantas.length === 0) {
        return <p className={styles.mensagem}>Nenhuma planta cadastrada ainda.</p>;
    }

    return (
        <div className={styles.container}>
            <h2 className={styles.titulo}>Minhas Plantas</h2>

            <div className={styles.lista}>
                {plantas.map(planta => (
                    <div key={planta.id} className={styles.card}>
                        <div className={styles.cardHeader}>
                            <div>
                                <h3 className={styles.nome}>{planta.nome}</h3>
                                <p className={styles.especie}>{planta.especie}</p>
                            </div>
                            <button
                                className={styles.botaoDeletar}
                                onClick={() => deletarPlanta(planta.id, planta.nome)}
                            >
                                🗑️
                            </button>
                        </div>
                        <div className={styles.tags}>
                            <span className={styles.tag}>{planta.tipo}</span>
                            <span className={styles.tag}>💧 {planta.frequenciaRega}</span>
                            <span className={styles.tag}>☀️ {planta.nivelLuz}</span>
                        </div>
                        {planta.descricao && <p className={styles.descricao}>{planta.descricao}</p>}
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ListaPlanta;