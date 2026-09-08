import styles from "./Header.module.css";

function Header() {
    return (
        <header className={styles.header}>
            <div className={styles.conteudo}>
                <div className={styles.logo}>
                    <span className={styles.icone}>🌱</span>
                    <h1 className={styles.titulo}>Meu Jardim</h1>
                </div>
                <p className={styles.subtitulo}>Tenha controle das suas plantas favoritas</p>
            </div>
        </header>
    );
}

export default Header;