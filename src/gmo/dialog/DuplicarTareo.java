package gmo.dialog;

import app.RunMain;
import static gmo.core.MainLite.*;
import gmo.utils.jkeys;
import static gmo.utils.jkeys.*;
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

    String IDTAREO, IDPLANILLA, IDUSUARIO;
    String NUMEROTAREO;
    JFrame Frame;
    JCallbackFrame callbackFrame;

    String ACTION = "DUPLI_TAREO";

    public DuplicarTareo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        init();

    }

    public DuplicarTareo(String IDTAREO, String IDPLANILLA, String IDUSUARIO, String NUMEROTAREO, Frame owner, boolean modal) {
        super(owner, modal);
        initComponents();
        this.ACTION = "DUPLI_TAREO";
        this.Frame = (JFrame) owner;
        this.IDTAREO = IDTAREO;
        this.IDPLANILLA = IDPLANILLA;
        this.IDUSUARIO = IDUSUARIO;
        this.NUMEROTAREO = NUMEROTAREO;
        init();
    }

    private void init() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        chooserFechaActual.setDate(cal.getTime());
        setSize(280, 220);
        edtUsuario.setText(IDUSUARIO);
        etEmpresa.setText(NUMEROTAREO);
    }

    private void Duplicar() {
        ExecHTTP.ExecPostProcedure_2022(Frame,
                gettin_pages.api_set(),
                "iddatabase,query",
                new Object[]{
                    jkeys.IDDATABASE2,
                    ExecHTTP.parseQL("exec UpDuplicarTareo ",
                            IDDATABASE,
                            IDEMPRESA,
                            IDPLANILLA,
                            chooserFechaActual.toStringDate(),
                            chooserFechaNueva.toStringDate(),
                            IDUSUARIO,
                            IDTAREO,
                            RunMain.INFO_HOST
                    )
                }, (String string) -> {
                    this.dispose();
                    if (callbackFrame != null)
                        callbackFrame.action(Frame);
                },
                () -> {//ACTION WARN
                });

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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        checkBox1 = new kevin.component.checkbox.CheckBox();
        checkBox2 = new kevin.component.checkbox.CheckBox();
        jPanel3 = new javax.swing.JPanel();
        etiqueta2 = new kevin.component.label.Etiqueta();
        etiqueta3 = new kevin.component.label.Etiqueta();
        etiqueta6 = new kevin.component.label.Etiqueta();
        etiqueta7 = new kevin.component.label.Etiqueta();
        etiqueta8 = new kevin.component.label.Etiqueta();
        jPanel4 = new javax.swing.JPanel();
        edtUsuario = new kevin.component.edittext.EditText();
        etEmpresa = new kevin.component.label.Etiqueta();
        edtPlanilla = new kevin.component.edittext.EditText();
        chooserFechaActual = new kevin.component.date.MaterialDateChooser();
        chooserFechaNueva = new kevin.component.date.MaterialDateChooser();
        btnDuplicar = new kevin.component.button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Duplicidad de Tareo");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(330, 420));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(320, 80));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel5.setOpaque(false);

        checkBox1.setText("Respetar Usuarios Originales");

        checkBox2.setText("Respetar Planillas Originales");

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        etiqueta2.setText("Usuario");
        jPanel3.add(etiqueta2);

        etiqueta3.setText("Empresa");
        jPanel3.add(etiqueta3);

        etiqueta6.setText("Planilla");
        jPanel3.add(etiqueta6);

        etiqueta7.setText("Fecha Origen");
        jPanel3.add(etiqueta7);

        etiqueta8.setText("Fecha Nueva");
        jPanel3.add(etiqueta8);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        edtUsuario.setLabel("Ingresar Usuario");
        jPanel4.add(edtUsuario);

        etEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etEmpresa.setText("");
        jPanel4.add(etEmpresa);

        edtPlanilla.setLabel("Ingresar Planilla");
        jPanel4.add(edtPlanilla);
        jPanel4.add(chooserFechaActual);
        jPanel4.add(chooserFechaNueva);

        btnDuplicar.setText("Aplicar");
        btnDuplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuplicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDuplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(checkBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDuplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 35, 14);
        jPanel2.add(jPanel5, gridBagConstraints);

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
    private kevin.component.checkbox.CheckBox checkBox1;
    private kevin.component.checkbox.CheckBox checkBox2;
    private kevin.component.date.MaterialDateChooser chooserFechaActual;
    private kevin.component.date.MaterialDateChooser chooserFechaNueva;
    private kevin.component.edittext.EditText edtPlanilla;
    private kevin.component.edittext.EditText edtUsuario;
    private kevin.component.label.Etiqueta etEmpresa;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private kevin.component.label.Etiqueta etiqueta6;
    private kevin.component.label.Etiqueta etiqueta7;
    private kevin.component.label.Etiqueta etiqueta8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
