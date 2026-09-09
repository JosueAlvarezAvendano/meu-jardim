import { useState, useEffect } from "react";
import Header from "./components/Header/Header";
import FormPlanta from "./components/FormPlanta/FormPlanta";
import ListaPlanta from "./components/ListaPlanta/ListaPlanta";
import styles from "./App.module.css";

function App() {

  const [plantas, setPlantas] = useState([]);
  const [loading, setLoading] = useState(true);

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

  useEffect(() => {
    buscarPlantas();
  }, []);

  return (
  <div className={styles.pagina}>
    <Header />
    <div className={styles.conteudo}>
      <FormPlanta onPlantaCadastrada={buscarPlantas} />
      <ListaPlanta plantas={plantas} loading={loading} onPlantaDeletada={buscarPlantas} />
    </div>
  </div>
);
}

export default App;