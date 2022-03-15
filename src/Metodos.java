import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Metodos {
    FileWriter fich;
    PrintWriter esc;
    GitHub github = null;
    GHRepository repo=null;
    //Metodo para crear un repositorio y guardar el token, si el repositorio esta creado utiliza el anterior token
    public GitHub Token(String pathalFich){
        File file=new File(pathalFich);
        //si existe el repositorio
        if(file.exists()){
            try {
                github = new GitHubBuilder()
                        .fromPropertyFile(pathalFich)
                        .build();
            } catch (IOException e) {
                //recogemos la excepcion
                System.out.println(e.getMessage());
            }
            //si no existe el repositorio
        }else{
            try {
                //crea un nuevo fichero para guardar las credenciales
                fich=new FileWriter(file , true);
                esc=new PrintWriter(fich);
                String tok=JOptionPane.showInputDialog("Introduce tu token");
                esc.print("oauth="+tok);
                github=new GitHubBuilder()
                        .withOAuthToken(tok)
                        .build();
            } catch (IOException e) {
                //recogemos la excepcion
                System.out.println(e.getMessage());
            } finally {
                esc.close();
            }
        }
        return github;
    }
    //Mettodo creacion de repostiroio
    public void crearRepositorio(){
        try {
            repo = github
                    //añadir nombre al repositorio que vamos a crear
                    .createRepository(JOptionPane.showInputDialog("Introduce el nombre del repositorio"))
                    .create();
        }catch (IOException e){
            //excepción si da error
            System.out.println(e.getMessage());
        }
    }
}