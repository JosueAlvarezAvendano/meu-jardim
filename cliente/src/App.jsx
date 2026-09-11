import { useState, useEffect } from "react";
import Header from "./components/Header/Header";
import FormPlanta from "./components/FormPlanta/FormPlanta";
import ListaPlanta from "./components/ListaPlanta/ListaPlanta";
import styles from "./App.module.css";

function App() {

  const [plantas, setPlantas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [termoBusca, setTermoBusca] = useState("");

  async function buscarPlantas() {
    setLoading(true);
    try {
      const resposta = await fetch("http://localhost:8080/plantas");
      if (resposta.status === 200) {
        const dados = await resposta.json();
        setPlantas(dados);
      } else {
        setPlantas([]);
      }
    } catch (error) {
      setPlantas([]);
    } finally {
      setLoading(false);
    }
  }

  async function buscarPorNome(nome) {

    // Se o campo estiver vazio, lista todas as plantas normalmente
    if (nome.trim() === "") {
      buscarPlantas();
      return;
    }

    setLoading(true);
    try {
      const resposta = await fetch(`http://localhost:8080/plantas/buscar?nome=${nome}`);

      if (resposta.status === 200) {
        const dados = await resposta.json();
        setPlantas(dados);
      } else {
        // 204 = nenhuma planta encontrada
        setPlantas([]);
      }
    } catch (error) {
      setPlantas([]);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    buscarPlantas();
  }, []);

  useEffect(() => {
    buscarPorNome(termoBusca);
  }, [termoBusca]);

  return (
    <div className={styles.pagina}>
        <Header />
        <div className={styles.conteudo}>
            <FormPlanta onPlantaCadastrada={buscarPlantas} />
            <div className={styles.ladoDireito}>
                <input
                    className={styles.campoBusca}
                    type="text"
                    placeholder="🔍 Buscar planta por nome..."
                    value={termoBusca}
                    onChange={(e) => setTermoBusca(e.target.value)}
                />
                <ListaPlanta
                    plantas={plantas}
                    loading={loading}
                    onPlantaDeletada={buscarPlantas}
                />
            </div>
        </div>
    </div>
);
}

export default App;