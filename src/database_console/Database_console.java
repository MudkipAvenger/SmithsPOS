package database_console;

class Database_console {

    public static void main(String args[]) {

  
        Database myDB = new Database();
        MainFrame jf = new MainFrame();
        jf.setData(myDB);
        jf.setVisible(true);
    }
    
}
