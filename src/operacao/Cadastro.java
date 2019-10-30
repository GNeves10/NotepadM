package operacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class Cadastro {

  private static String nomeArquivo = "cadastro.ser";
  private static List<Usuario> cadastrados = carregarCadastro();

  public static List<Usuario> getCadastrados() {
    if (cadastrados == null)
      cadastrados = carregarCadastro();
    return cadastrados;
  }

  private static List<Usuario> carregarCadastro() {
    List<Usuario> cadastro = new ArrayList<Usuario>();
    try {
      File f = new File(nomeArquivo);
      if (!f.exists()) 
        return cadastro;
      BufferedReader csvReader = new BufferedReader(new FileReader(nomeArquivo));
      String row;
      while ((row = csvReader.readLine()) != null) {
        String[] dados = row.split(",");
        cadastro.add(new Usuario(dados[0], dados[1], dados[2]));
      }
      csvReader.close();

    } catch (IOException ex) {
      System.out.println("IOException lançada");
    }
    return cadastro;
  }

  public static void salvarCadastro() {
    try {
      File f = new File(nomeArquivo);
      if (!f.exists())
        f.createNewFile();
      FileWriter csvWriter = new FileWriter(nomeArquivo);
      for (Usuario u : cadastrados) {
        csvWriter.append(u.getNome() + ",");
        csvWriter.append(u.getEmail() + ",");
        csvWriter.append(u.getSenha() + "\n");
      }
      csvWriter.close();
    } catch (IOException ex) {
      System.out.println("IOException lançada.");
      ex.printStackTrace();
    }
  }

  public static void cadastrarNovo(String nome, String email, String senha) {
    if (cadastrados == null)
      cadastrados = carregarCadastro();
    cadastrados.add(new Usuario(nome, email, senha));
    salvarCadastro();
  }

}
