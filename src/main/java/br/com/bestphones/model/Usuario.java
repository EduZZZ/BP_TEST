package br.com.bestphones.model;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private boolean registro_deletado;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senha, String cargo, boolean registro_deletado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.registro_deletado = registro_deletado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isRegistro_deletado() {
        return registro_deletado;
    }

    public void setRegistro_deletado(boolean registro_deletado) {
        this.registro_deletado = registro_deletado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cargo=" + cargo + ", registro_deletado=" + registro_deletado + '}';
    }
}
