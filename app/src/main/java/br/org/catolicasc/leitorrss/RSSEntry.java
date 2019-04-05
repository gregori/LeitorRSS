package br.org.catolicasc.leitorrss;

public class RSSEntry {
    private String nome;
    private String artista;
    private String dataLancamento;
    private String sumario;
    private String urlImagem;

    public RSSEntry() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    @Override
    public String toString() {
        return "nome=" + nome + '\n' +
                ", artista=" + artista + '\n' +
                ", dataLancamento=" + dataLancamento + '\n' +
                ", urlImagem=" + urlImagem + '\n';
    }
}
