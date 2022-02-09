package gmo.dialog;

import app.RunMain;
import gmo.utils.jkeys;
import java.awt.Frame;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import jmugi.voids.JCallbackFrame;
import kevin.component.toast.Toast;
import utils.ExecHTTP;

/**
 @author kevin leandro
 */
public class DuplicarTareo extends javax.swing.JDialog {

    String IDTAREO, IDPLANILLA, IDTURNO, IDUSUARIO;
    String NUMEROTAREO;
    String FECHA;
    JFrame Frame;
    JCallbackFrame callbackFrame;

    String ACTION = "DUPLI_TAREO";

    public DuplicarTareo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        init();

    }

    public DuplicarTareo(java.awt.Frame parent, boolean modal, String IDREGISTRO, String FECHA, String ACTION) {
        super(parent, modal);
        initComponents();
        this.IDTAREO = IDREGISTRO;
        this.FECHA = FECHA;
        this.ACTION = ACTION;
        init();

    }

    public DuplicarTareo(String IDTAREO, String IDPLANILLA, String IDTURNO, String IDUSUARIO, String NUMEROTAREO, Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();
        this.ACTION = "DUPLI_TAREO";
        this.Frame = (JFrame) owner;
        this.IDTAREO = IDTAREO;
        this.IDPLANILLA = IDPLANILLA;
        this.IDTURNO = IDTURNO;
        this.IDUSUARIO = IDUSUARIO;
        this.NUMEROTAREO = NUMEROTAREO;
        init();
    }

    private void init() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        chooserFecha.setDate(cal.getTime());
        setSize(280, 220);
        etUsuario.setText(IDUSUARIO);
        etTareo.setText(NUMEROTAREO);
    }

    private void Duplicar() {

        switch (ACTION) {
            case "DUPLI_TAREO":

                ExecHTTP.ExecPostProcedure(Frame,
                        RunMain.gettin_pages.SetQuery(),
                        new String[]{"iddatabase2", "query"},
                        new Object[]{
                            jkeys.BD2,
                            ExecHTTP.parseQL("exec SetTareoNiDu ",
                                    IDTAREO,
                                    chooserFecha.toStringDate(),
                                    IDUSUARIO,
                                    jkeys.IDDATABASE,
                                    IDPLANILLA,
                                    IDTURNO,
                                    RunMain.INFO_HOST
                            )
                        },
                        () -> {//ACTION DONE
                            this.dispose();
                            if (callbackFrame != null)
                                callbackFrame.action(Frame);
                        },
                        () -> {//ACTION WARN
                        });
                break;
            case "CHANGEDATE_COSECHA":
                ExecHTTP.ExecPostProcedure(Frame,
                        RunMain.gettin_pages.SetQuery(),
                        new String[]{"iddatabase2", "query"},
                        new Object[]{
                            jkeys.BD2,
                            ExecHTTP.parseQL("exec upChangeDate  ",
                                    jkeys.IDDATABASE,
                                    jkeys.IDEMPRESA,
                                    "COSECHA",
                                    IDTAREO,
                                    RunMain.INFO_HOST
                            )
                        },
                        Toast.makeText((JFrame) Frame, "Se Cambiaron los Datos Correctamente!", Toast.Style.SUCCESS)::display,
                        Toast.makeText((JFrame) Frame, "HA OCURRIDO UN INCIDENTE, VALIDAR LOS DATOS", Toast.Style.ERROR)::display
                );
                break;
            case "CHANGEDATE_TAREO":
                break;
        }

    }

    public JCallbackFrame getCallbackFrame() {
        return callbackFrame;
    }

    public void setCallbackFrame(JCallbackFrame callbackFrame) {
        this.callbackFrame = callbackFrame;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        etiqueta2 = new kevin.component.label.Etiqueta();
        etiqueta3 = new kevin.component.label.Etiqueta();
        etTareo = new kevin.component.label.Etiqueta();
        etUsuario = new kevin.component.label.Etiqueta();
        chooserFecha = new kevin.component.date.MaterialDateChooser();
        etiqueta6 = new kevin.component.label.Etiqueta();
        btnDuplicar = new kevin.component.button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Duplicidad de Tareo");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(320, 80));

        etiqueta2.setText("Usuario");

        etiqueta3.setText("# Registro");

        etTareo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etTareo.setText("");

        etUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etUsuario.setText("");

        etiqueta6.setText("Fecha");

        btnDuplicar.setText("Aplicar");
        btnDuplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuplicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(etiqueta2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(etUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(etiqueta3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etTareo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(etiqueta6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chooserFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                    .addComponent(btnDuplicar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etTareo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(chooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(btnDuplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDuplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuplicarActionPerformed
        Duplicar();
    }//GEN-LAST:event_btnDuplicarActionPerformed

    /**
     @param args the command line arguments
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
            java.util.logging.Logger.getLogger(DuplicarTareo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DuplicarTareo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DuplicarTareo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DuplicarTareo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DuplicarTareo dialog = new DuplicarTareo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button btnDuplicar;
    private kevin.component.date.MaterialDateChooser chooserFecha;
    private kevin.component.label.Etiqueta etTareo;
    private kevin.component.label.Etiqueta etUsuario;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private kevin.component.label.Etiqueta etiqueta6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
