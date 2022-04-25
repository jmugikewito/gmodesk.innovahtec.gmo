/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gmo.dialog;

import app.RunMain;
import static gmo.core.MainLite.gettin_pages;
import gmo.utils.jkeys;
import static gmo.utils.jkeys.IDDATABASE;
import static gmo.utils.jkeys.IDEMPRESA;
import java.awt.Frame;
import java.awt.Window;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import jmugi.voids.JCallbackBool;
import jmugi.voids.JCallbackFrame;
import kevin.component.frame.MaterialDialog;
import utils.ExecHTTP;

/**

 @author kevin
 */
public class panDuplicarTareo extends javax.swing.JPanel {

    public MaterialDialog DIALOG;
    public Window FRAME;
    public String TITLE;

    String IDTAREO, IDPLANILLA, IDUSUARIO;
    String NUMEROTAREO;
    JFrame Frame;
    JCallbackFrame callbackFrame;

    public panDuplicarTareo() {
        initComponents();
    }

    public panDuplicarTareo(Window frame,
                            boolean modal,
                            String title,
                            boolean encript
    ) {
        this.FRAME = frame;
        this.TITLE = title;
        DIALOG = new MaterialDialog((Frame) FRAME, true);
        DIALOG.setTitle(TITLE);

        init();

    }

    private void init() {

        chkPlanOrigi.setEvent((boolean estado) -> {

        });

        this.initComponents();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        dateFechaActual.setDate(cal.getTime());
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
                            dateFechaActual.toStringDate(),
                            dateFechaNueva.toStringDate(),
                            IDUSUARIO,
                            IDTAREO,
                            RunMain.INFO_HOST
                    )
                }, (String string) -> {
                    DIALOG.dispose();
                    if (callbackFrame != null)
                        callbackFrame.action(Frame);
                },
                () -> {//ACTION WARN
                });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel5 = new javax.swing.JPanel();
        chkUserOrigi = new kevin.component.checkbox.CheckBox();
        chkPlanOrigi = new kevin.component.checkbox.CheckBox();
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
        dateFechaActual = new kevin.component.date.MaterialDateChooser();
        dateFechaNueva = new kevin.component.date.MaterialDateChooser();
        btnAplicar = new kevin.component.button.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridBagLayout());

        jPanel5.setOpaque(false);

        chkUserOrigi.setSelected(true);
        chkUserOrigi.setText("Respetar Usuarios Originales");
        chkUserOrigi.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkUserOrigiStateChanged(evt);
            }
        });

        chkPlanOrigi.setSelected(true);
        chkPlanOrigi.setText("Respetar Planillas Originales");
        chkPlanOrigi.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkPlanOrigiStateChanged(evt);
            }
        });

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

        edtUsuario.setEnabled(false);
        edtUsuario.setLabel("Ingresar Usuario");
        jPanel4.add(edtUsuario);

        etEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etEmpresa.setText("");
        jPanel4.add(etEmpresa);

        edtPlanilla.setEnabled(false);
        edtPlanilla.setLabel("Ingresar Planilla");
        jPanel4.add(edtPlanilla);
        jPanel4.add(dateFechaActual);
        jPanel4.add(dateFechaNueva);

        btnAplicar.setText("Aplicar");
        btnAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkUserOrigi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkPlanOrigi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkUserOrigi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(chkPlanOrigi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 100, 10);
        add(jPanel5, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarActionPerformed
        Duplicar();
    }//GEN-LAST:event_btnAplicarActionPerformed

    private void chkUserOrigiStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkUserOrigiStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_chkUserOrigiStateChanged

    private void chkPlanOrigiStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkPlanOrigiStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPlanOrigiStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button btnAplicar;
    private kevin.component.checkbox.CheckBox chkPlanOrigi;
    private kevin.component.checkbox.CheckBox chkUserOrigi;
    private kevin.component.date.MaterialDateChooser dateFechaActual;
    private kevin.component.date.MaterialDateChooser dateFechaNueva;
    private kevin.component.edittext.EditText edtPlanilla;
    private kevin.component.edittext.EditText edtUsuario;
    private kevin.component.label.Etiqueta etEmpresa;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private kevin.component.label.Etiqueta etiqueta6;
    private kevin.component.label.Etiqueta etiqueta7;
    private kevin.component.label.Etiqueta etiqueta8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
