package crud;

import java.util.*;

import javax.swing.JOptionPane;

import java.sql.*;

public class crudd  {
    public static void connect() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:desejo.db")) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if not exists Usuarios(id INTEGER PRIMARY KEY AUTOINCREMENT, nomeUser text, desejo text)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void inserir(String testando,String desejo) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:desejo.db")) {
            System.out.println("CONEXÃO REALIZADA !!!!");
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO Usuarios (nomeUser,desejo) VALUES ('"+testando+"','"+desejo+"')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void alterar(String desejo, String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:desejo.db")) {

            Statement statement = connection.createStatement();
            statement.execute("UPDATE Usuarios SET desejo = '"+desejo+"' WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void listar() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:desejo.db")) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Usuarios");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nomeUser = resultSet.getString("nomeUser");
                String desejo = resultSet.getString("desejo");
                System.out.println(id + " - " + nomeUser + " - " + desejo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Map<String, String> pegar(String valor) {
        Map<String, String> dados = new HashMap<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:desejo.db")) {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Usuarios WHERE id = " + valor);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                dados.put("id",resultSet.getString("id"));
                dados.put("nomeUser",resultSet.getString("nomeUser"));
                dados.put("desejo",resultSet.getString("desejo"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dados;
    }
    public static void apagar(String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:desejo.db")) {
           Statement statement = connection.createStatement();
           statement.execute("DELETE FROM Usuarios WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args)  {
        connect();
        String opcao;
       
            JOptionPane.showMessageDialog(null, "                   OLÁ !! SEJA BEM VINDO AO\n                              SEUDESEJO\nAQUI NÓS ANOTAMOS TODOS OS SEUS DESEJOS !!", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);

            ArrayList<User> users = new ArrayList<User>();
            ArrayList<UserEmail> usersE = new ArrayList<UserEmail>();
            ArrayList<String> userLogado = new ArrayList<String>();
            ArrayList<String> userLogadoE = new ArrayList<String>();

            boolean confLogin = false;
            boolean confLoginE = false;
            // String userLogado; 

         do {

            JOptionPane.showMessageDialog(null, "           PÁGINA INICIAL:\n [1] - FAZER CADASTRO\n [2] - FAZER LOGIN\n [0] - SAIR", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
    
            opcao = JOptionPane.showInputDialog(null, "Escolha uma opção:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);

    
            if (opcao.equals("1")){

                JOptionPane.showMessageDialog(null, " CADASTRO:\n[1] - CADASTRO COM EMAIL\n[2] - CADASTRO COM NOME DE USUÁRIO", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                
                String nome_email = JOptionPane.showInputDialog(null, "DIGITE SUA OPÇÃO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);

                if(nome_email.equals("1")){

                    String email = JOptionPane.showInputDialog(null, "DIGITE SEU EMAIL DE USUÁRIO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);

                    String senha = JOptionPane.showInputDialog(null, "DIGITE SUA SENHA DE USUÁRIO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
    
                    UserEmail userE = new UserEmail(email, senha);
                    // user.setNomeUser(nomeUser);
                    // user.setSenha(senha);
    
                    System.out.println(userE.getNomeUser());
                    System.out.println(userE.getEmail());
                    System.out.println(userE.getSenha());
                    
                    usersE.add(userE);
    
                    for (UserEmail testeE : usersE) {
                        System.out.println(testeE.getNomeUser());
                        System.out.println(testeE.getEmail());
                        System.out.println(testeE.getSenha());
                    }
    
                    JOptionPane.showMessageDialog(null, "      CADASTRO REALIZADO COM SUCESSO !!\nAPERTE NO BOTÃO PARA VOLTAR A PÁGINA INICIAL !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);

                }

                if(nome_email.equals("2")){

                    String nomeUser = JOptionPane.showInputDialog(null, "DIGITE SEU NOME DE USUÁRIO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);

                    String senha = JOptionPane.showInputDialog(null, "DIGITE SUA SENHA DE USUÁRIO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
    
                    User user = new User(nomeUser, senha);
                    // user.setNomeUser(nomeUser);
                    // user.setSenha(senha);
    
                    System.out.println(user.getNomeUser());
                    
                    users.add(user);
    
                    for (User teste : users) {
                        System.out.println(teste.getNomeUser());
                        System.out.println(teste.getSenha());
                    }
    
                    JOptionPane.showMessageDialog(null, "      CADASTRO REALIZADO COM SUCESSO !!\nAPERTE NO BOTÃO PARA VOLTAR A PÁGINA INICIAL !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE); 
                }
                
            }

            if(opcao.equals("2")){
                
                JOptionPane.showMessageDialog(null, " LOGIN:\n[1] - LOGIN COM EMAIL\n[2] - LOGIN COM NOME DE USUÁRIO", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                
                String op_login = JOptionPane.showInputDialog(null, "DIGITE SUA OPÇÃO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);

                if(op_login.equals("1")){

                    String emailLogin = JOptionPane.showInputDialog(null, "DIGITE SEU EMAIL CADASTRADO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);

                    String senhaLogin = JOptionPane.showInputDialog(null, "DIGITE SUA SENHA DE USUÁRIO CADASTRADO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);

                    for(int i = 0; i<usersE.size();i++){
                        UserEmail userTempE = usersE.get(i);

                        if(emailLogin.equals(userTempE.getEmail())){
                            System.out.println("USUÁRIO LOGADO !");
                            System.out.println("Email: " + userTempE.getEmail());
                            System.out.println("SENHA: " + userTempE.getSenha());
                            confLoginE = true;
                            userLogadoE.add(emailLogin);
                            JOptionPane.showMessageDialog(null, "USUÁRIO LOGADO !!", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                }

                if(op_login.equals("2")){

                    String nomeUserLogin = JOptionPane.showInputDialog(null, "DIGITE SEU NOME DE USUÁRIO CADASTRADO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);

                    String senhaLogin = JOptionPane.showInputDialog(null, "DIGITE SUA SENHA DE USUÁRIO CADASTRADO:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);

                    for(int i = 0; i<users.size();i++){
                        User userTemp = users.get(i);

                        if(nomeUserLogin.equals(userTemp.getNomeUser())){
                            System.out.println("USUÁRIO LOGADO !");
                            System.out.println("NOME DO USUÁRIO: " + userTemp.getNomeUser());
                            System.out.println("SENHA: " + userTemp.getSenha());
                            confLogin = true;
                            userLogado.add(nomeUserLogin);
                            JOptionPane.showMessageDialog(null, "USUÁRIO LOGADO !!", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                }
            }

            if(opcao.equals("0")){
                System.exit(0);
            }

            if(confLoginE == true){
                confLoginE = false;
                while(true){

                    int ultimoindice = userLogadoE.size() - 1;
                    String testando = userLogadoE.get(ultimoindice);
                    
                    JOptionPane.showMessageDialog(null, "-------------- SeuDesejo ------------\n             [1] - Criar Desejo\n             [2] - Listar Desejo\n             [3] - Editar Desejo\n             [4] - Deletar Desejo\n             [5] - Sair", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);

          
                    String escolha = JOptionPane.showInputDialog(null, "Escolha uma opção:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                    if (escolha.equals("1")){
                        System.out.println("Novo Desejo \n ");
                        String desejo, r;
                    
                        desejo = JOptionPane.showInputDialog(null, "Escreva seu Desejo:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                        JOptionPane.showMessageDialog(null, "DESEJA SALVAR?\n" + "\nDesejo:" + desejo, "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("\n\n DESEJA SALVAR? \n ");
                        System.out.println("Desejo: "+desejo);
        
                        r = JOptionPane.showInputDialog(null, "PRA CONFIRMAR [S/N]:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                        if (r.equals("s")){
                            inserir(testando,desejo);
                            JOptionPane.showMessageDialog(null, "DESEJO INSERIDO !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } 
        
                    else if(escolha.equals("2")){
                    
                        JOptionPane.showMessageDialog(null, "------ LISTAR DESEJOS CADASTRADOS ------\n                     OLHA O PROMPT !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        listar();
                    } 
                    
                    else if(escolha.equals("3")){
                        String desejo,r,i;
                        
        
                        JOptionPane.showMessageDialog(null, "------ EDITAR UM DESEJO ------", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
        
                        
                        i = JOptionPane.showInputDialog(null, "DIGITE O ID DO DESEJO PARA EDITAR:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                        Map<String, String> dados = pegar(i);
                        if (dados.size() == 3){
        
                            desejo = JOptionPane.showInputDialog(null, "DIGITE O DESEJO QUE QUER COLOCAR NO LUGAR:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                            System.out.println("\n\n ANTES : \n");
                            System.out.println("Desejo : " + dados.get("desejo"));
                            System.out.println("\n DEPOIS :");
                            System.out.println("Desejo : " + desejo);
                           
                            r = JOptionPane.showInputDialog(null, "Deseja fazer essa alteração: [S/N]:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
                            if (r.equals("s")){
                                alterar(desejo,i);
                                JOptionPane.showMessageDialog(null, "DESEJO ALTERADO !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } 
                        else {
                            JOptionPane.showMessageDialog(null, "DESEJO NÃO LOCALIZADO !!", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        }
        
                    } 
                    
                    else if(escolha.equals("4")){
                        System.out.println(" ------ APAGAR UM DESEJO ------ \n");
                        String i,r;
                  
                        i = JOptionPane.showInputDialog(null, "DIGITE O ID DO DESEJO PARA DELETAR:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
                        Map<String, String> dados = pegar(i);
                        if (dados.size() == 3){
                            System.out.println("\n DADOS A SEREM APAGADOS: \n");
                            System.out.println("Desejo : " + dados.get("desejo"));
                            
                            r = JOptionPane.showInputDialog(null, "DESEJA REALMENTE APAGAR? [S/N]:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
                            if (r.equals("s")){
                                apagar(i);
                                JOptionPane.showMessageDialog(null, "DESEJO APAGADO !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } 
                        else {
                            JOptionPane.showMessageDialog(null, "DESEJO NÃO ENCONTRADO !!", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } 
                    
                    else if(escolha.equals("5")){
                        System.out.println("SAINDO ");
                        JOptionPane.showMessageDialog(null, "SAINDO...", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    } 
                    
                    else {
                        System.out.println("OPÇÃO INVALIDA \n ");
                        JOptionPane.showMessageDialog(null, "OPÇÃO INVALIDA", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            

            if(confLogin == true){
                confLogin = false;
                while(true){

                    int ultimoindice = userLogado.size() - 1;
                    String testando = userLogado.get(ultimoindice);
                    
                    JOptionPane.showMessageDialog(null, "-------------- SeuDesejo ------------\n             [1] - Criar Desejo\n             [2] - Listar Desejo\n             [3] - Editar Desejo\n             [4] - Deletar Desejo\n             [5] - Sair", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);

          
                    String escolha = JOptionPane.showInputDialog(null, "Escolha uma opção:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                    if (escolha.equals("1")){
                        System.out.println("Novo Desejo \n ");
                        String desejo, r;
                    
                        desejo = JOptionPane.showInputDialog(null, "Escreva seu Desejo:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                        JOptionPane.showMessageDialog(null, "DESEJA SALVAR?\n" + "\nDesejo:" + desejo, "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("\n\n DESEJA SALVAR? \n ");
                        System.out.println("Desejo: "+desejo);
        
                        r = JOptionPane.showInputDialog(null, "PRA CONFIRMAR [S/N]:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                        if (r.equals("s")){
                            inserir(testando,desejo);
                            JOptionPane.showMessageDialog(null, "DESEJO INSERIDO !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } 
        
                    else if(escolha.equals("2")){
                        JOptionPane.showMessageDialog(null, "------ LISTAR DESEJOS CADASTRADOS ------\n                     OLHA O PROMPT !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        listar();
                    } 
                    
                    else if(escolha.equals("3")){
                        String desejo,r,i;
                        
                        JOptionPane.showMessageDialog(null, "------ EDITAR UM DESEJO ------", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
        
                        
                        i = JOptionPane.showInputDialog(null, "DIGITE O ID DO DESEJO PARA EDITAR:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                        Map<String, String> dados = pegar(i);
                        if (dados.size() == 3){
        
                            desejo = JOptionPane.showInputDialog(null, "DIGITE O DESEJO QUE QUER COLOCAR NO LUGAR:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
        
                            System.out.println("\n\n ANTES : \n");
                            System.out.println("Desejo : " + dados.get("desejo"));
                            System.out.println("\n DEPOIS :");
                            System.out.println("Desejo : " + desejo);
                           
                            r = JOptionPane.showInputDialog(null, "Deseja fazer essa alteração: [S/N]:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
                            if (r.equals("s")){
                                alterar(desejo,i);
                                JOptionPane.showMessageDialog(null, "DESEJO ALTERADO !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } 
                        else {
                            JOptionPane.showMessageDialog(null, "DESEJO NÃO LOCALIZADO !!", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                            
                        }
        
                    } 
                    
                    else if(escolha.equals("4")){
                        System.out.println(" ------ APAGAR UM DESEJO ------ \n");
                        String i,r;
                  
                        i = JOptionPane.showInputDialog(null, "DIGITE O ID DO DESEJO PARA DELETAR:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
                        Map<String, String> dados = pegar(i);
                        if (dados.size() == 3){
                            System.out.println("\n DADOS A SEREM APAGADOS: \n");
                            System.out.println("Desejo : " + dados.get("desejo"));
                            
                            r = JOptionPane.showInputDialog(null, "DESEJA REALMENTE APAGAR? [S/N]:", "SEUDESEJO", JOptionPane.QUESTION_MESSAGE);
                            if (r.equals("s")){
                                apagar(i);
                                JOptionPane.showMessageDialog(null, "DESEJO APAGADO !", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } 
                        else {
                            JOptionPane.showMessageDialog(null, "DESEJO NÃO ENCONTRADO !!", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } 
                    
                    else if(escolha.equals("5")){
                        System.out.println("SAINDO ");
                        JOptionPane.showMessageDialog(null, "SAINDO...", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    } 
                    
                    else {
                        System.out.println("OPÇÃO INVALIDA \n ");
                        JOptionPane.showMessageDialog(null, "OPÇÃO INVALIDA", "SEUDESEJO", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            
        } while(opcao!="0");
      
    }
}