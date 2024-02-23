import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GetterSetterUML extends JFrame implements ActionListener {    

    private JTextArea jtaOben;
    private JTextArea jtaMitte;
    private JTextArea jtaUnten;
    private JButton jbGo;

    public GetterSetterUML() {
        setTitle("Generate Getter & Setter & UML");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel inhalt = new JPanel(new GridLayout(3,1));
        
        jtaOben = new JTextArea();
        jtaOben.setMargin(new Insets(5,5,5,5));
        jtaOben.setTabSize(2);
        jtaOben.setRows(10);
        jtaOben.setFont(new Font("Courier", Font.PLAIN, 12));
        jtaOben.setBackground(new Color(255,250,250));        
        inhalt.add(new JScrollPane(jtaOben));

        jtaMitte = new JTextArea();
        jtaMitte.setMargin(new Insets(5,5,5,5));
        jtaMitte.setTabSize(2);
        jtaMitte.setFont(new Font("Courier", Font.PLAIN, 12));
        jtaMitte.setBackground(new Color(250,255,250));
        jtaMitte.setEditable(false);
        inhalt.add(new JScrollPane(jtaMitte));
        
        jtaUnten = new JTextArea();
        jtaUnten.setMargin(new Insets(5,5,5,5));
        jtaUnten.setTabSize(2);
        jtaUnten.setFont(new Font("Courier", Font.PLAIN, 12));
        jtaUnten.setBackground(new Color(250,250,255));
        jtaUnten.setEditable(false);
        inhalt.add(new JScrollPane(jtaUnten));

        add(inhalt, BorderLayout.CENTER);
        jbGo = new JButton("Generate Getter & Setter & UML");
        jbGo.addActionListener(this);
        add(jbGo, BorderLayout.SOUTH);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(jbGo)) {
            jtaMitte.setText("");
            jtaUnten.setText("");
            String[] attribs = jtaOben.getText().split("\n");
            String jpUntenText = "";
            for (int i=0; i<attribs.length; i++) {                
                String var = attribs[i];
                if (var.length() > 0) {
                    var = var.replace(";", "");
                    if (var.contains("=")) {
                        var = var.substring(0, var.indexOf("="));
                    }                    
                    var = var.trim();
                    var = var.strip();
                    String[] teile = var.split(" ");
                    if (teile.length >= 2) {
                        String varName = teile[teile.length-1];
                        String varTyp = teile[teile.length-2];                                                
                        String fst = varName.charAt(0) + "";                    
                        String pVarName = fst.toUpperCase() + varName.substring(1);
                        jtaMitte.append("public " + varTyp + " " + "gib" + pVarName + "() {\n\treturn " + varName + ";\n}\n\n");
                        jtaMitte.append("public void " +  "setze" + pVarName + "(" + varTyp + " p" + pVarName + ") {\n\t" + varName + " = p" + pVarName + ";\n}\n\n");                        
                        
                        jtaUnten.append("- " + varName + " : " + varTyp + "\n");
                        
                        //jtaUnten
                        jpUntenText += "+ gib" + pVarName + "() : " + varTyp + "\n";
                        jpUntenText += "+ setze" + pVarName + "(p" + pVarName + " : " + varTyp + ") : void \n";
                    }
                }
            }
            jtaUnten.append("\n");
            jtaUnten.append(jpUntenText);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GetterSetterUML app = new GetterSetterUML();
                    app.setVisible(true);
                }
            });
    }
}
