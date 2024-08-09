/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package calculadora;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author edoar
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }
    
    public static double calcular(String expressao){
        List<String> tokens = converterParaRPN(expressao);
        return avaliarRPN(tokens);
    }
    
    public static List<String> converterParaRPN(String expressao){
        List<String> resultado = new ArrayList<>();
        Stack<Character> operadores = new Stack<>();
        
        StringBuilder numero = new StringBuilder();
        for (char c : expressao.toCharArray()){
            if (Character.isDigit(c) || c == '.' ){
                numero.append(c);
            }
            else {
                if (numero.length() > 0 ){
                    resultado.add(numero.toString());
                    numero.setLength(0);
                }
                if (c == '('){
                    operadores.push(c);
                }
                else if (c == ')'){
                    while (!operadores.isEmpty() && precedencia(c) <= precedencia(operadores.peek()) ){
                        resultado.add(String.valueOf(operadores.pop()));
                    }
                    operadores.push(c);
                }
            }
        }
        if (numero.length() > 0){
            resultado.add(numero.toString());
        }
        while (!operadores.isEmpty()){
            resultado.add(String.valueOf(operadores.pop()));
        }
        return resultado;
    }
    public static double avaliarRPN(List<String> tokens){
        Stack<Double> valores = new Stack<>();
        for (String token : tokens){
            if (isNumero(token)){
                valores.push(Double.valueOf(token));
            }else if (isOperator(token.charAt(0))){
                double b = valores.pop();
                double a = valores.pop();
                double resultado = aplicarOperador(a, b, token.charAt(0));
                valores.push(resultado);
            }
        }
        return valores.pop();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
