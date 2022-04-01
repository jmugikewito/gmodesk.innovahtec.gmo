package gmo.dialog;

import gmo.dialog.BuscarDialog;
import color.MaterialColor;
import static gmo.core.MainLite.*;
import acore.principalvalues;
import app.RunMain;
import gmo.utils.jkeys;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import jmugi.voids.JCallback;
import jmugi.voids.JMethods;
import utils.ExecHTTP;

/**
 *
 * @author Asus
 */
public class NuevoSubTareoOnLine extends javax.swing.JDialog {

    JFrame Frame;
    int NEWITEM = 0;
    String IDTAREO, ULTIMOITEM, ESRENDIM;
    BuscarDialog buscarCultivoVariedad;
    BuscarDialog buscarLaborActividad;
    BuscarDialog buscarConsumidores;
    JCallback callback;

    public NuevoSubTareoOnLine(boolean modal, JFrame frame, String idtareo, String ultimoItem, JCallback callback) {
        super(frame, modal);
        initComponents();
        this.Frame = frame;
        this.ULTIMOITEM = ultimoItem;
        this.IDTAREO = idtareo;
        this.callback = callback;
        initBuscadores();
    }

    private void initBuscadores() {
        buscarCultivoVariedad = new BuscarDialog(
                Frame,
                true,
                RunMain.CONECT.con,
                "idcultivo, cultivo, idvariedad, variedad, idcultivovariedad, cultivovariedad",
                "0, 0, 120, 200, 120, 200, 0, 0",
                ExecHTTP.parseQuery("select idcultivo, cultivo, idvariedad, variedad, idcultivovariedad, cultivovariedad\n"
                        + "from cultivovariedad\n"
                        + "where iddatabase = ?1 \n"
                        + "  and idempresa = ?2;",
                        true,
                        jkeys.IDDATABASE,
                        jkeys.IDEMPRESA),
                true,
                true
        );
        buscarCultivoVariedad.setTitle("Busqueda de Cultivo Variedad");
    }

    private void initActividadLabor(String idcultivo, String idvariedad) {
        buscarLaborActividad = new BuscarDialog(
                Frame,
                true,
                RunMain.CONECT.con,
                "idlabor,labor,idactividad,actividad,esrendimiento",
                "100, 280, 100, 280",
                chkConfigSubTareos.isSelected()
                ? (ExecHTTP.parseQuery("SELECT\n"
                        + "  DISTINCT\n"
                        + "  lab.idlabor,\n"
                        + "  ltrim(rtrim(lab.descripcion)) labor,\n"
                        + "  lab.idactividad,\n"
                        + "  ltrim(rtrim(ac.descripcion))  actividad,"
                        + "  ac.POR_RENDIMIENTO esrendimiento\n"
                        + "FROM LABOR lab\n"
                        + "  INNER JOIN ACTIVIDAD AC ON AC.idactividad = lab.idactividad AND Ac.idempresa = lab.idempresa\n"
                        + "  INNER JOIN movsubtareoconfig mov ON mov.idactividad = lab.idactividad AND mov.idlabor = lab.idlabor\n"
                        + "                                      AND mov.idempresa = lab.idempresa\n"
                        + "WHERE mov.idcultivo =?3 AND mov.idvariedad = ?4 AND mov.iddatabase = ?1 AND lab.idempresa = ?2 \n"
                        + "order by actividad;",
                        true,
                        jkeys.IDDATABASE, jkeys.IDEMPRESA, idcultivo, idvariedad))
                : (ExecHTTP.parseQuery(
                        "SELECT\n"
                        + "  ltrim(rtrim(lab.idlabor)) idlabor,\n"
                        + "  ltrim(rtrim(lab.descripcion)) labor,\n"
                        + "  ltrim(rtrim(lab.idactividad)) idactividad,\n"
                        + "  ltrim(rtrim(ac.descripcion))  actividad,\n"
                        + "  ac.POR_RENDIMIENTO esrendimiento\n"
                        + "FROM labores LAB\n"
                        + "  INNER JOIN actividad ac ON ac.idactividad = lab.idactividad AND ac.idempresa = lab.idempresa\n"
                        + "WHERE lab.idempresa=?1 and lab.estado = ?2 AND ac.estado = ?3;",
                        true,
                        jkeys.IDEMPRESA, 1, 1
                )),
                true,
                true);
        buscarLaborActividad.setTitle("Busqueda de Labores y Actividades");
    }

    private void initCOnsumidor(String idcultivo, String idvariedad, String idactividad, String idlabor) {
        buscarConsumidores = new BuscarDialog(
                (Frame) Frame,
                true,
                RunMain.CONECT.con,
                "idconsumidor,descripcion",
                "120,360",
                chkConfigSubTareos.isSelected()
                ? ExecHTTP.parseQuery("SELECT\n"
                        + " DISTINCT\n"
                        + " mov.idconsumidor,\n"
                        + " co.descripcion\n"
                        + " FROM CONSUMIDOR co\n"
                        + " INNER JOIN movsubtareoconfig mov ON mov.idempresa = co.idempresa AND co.idconsumidor = mov.idconsumidor\n"
                        + " WHERE\n"
                        + "  mov.idcultivo = ?3 AND mov.idvariedad = ?4 AND mov.iddatabase = ?1 AND mov.idempresa = ?2  AND\n"
                        + "  mov.idactividad = ?5  AND  mov.idlabor = ?6 ;", true,
                        jkeys.IDDATABASE,
                        jkeys.IDEMPRESA,
                        idcultivo,
                        idvariedad,
                        idactividad,
                        idlabor
                )
                : ExecHTTP.parseQuery("select ltrim(rtrim(idconsumidor)) idconsumidor,ltrim(rtrim(descripcion)) descripcion "
                        + "from consumidor "
                        + "where TIPO in ('T','F','R','M','O','X') and idempresa=?1  "
                        + (jkeys.RUC.equals("20103272964") ? " and estado=1 and FECHA_BAJA IS NULL" : " and estado=1 and FECHA_BAJA IS NULL;"), true,
                        jkeys.IDEMPRESA),
                true,
                true
        );
        buscarConsumidores.setTitle("Busqueda de Consumidores");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnValidate = new kevin.component.button.ButtonMaterialIcon();
        etiqueta1 = new kevin.component.label.Etiqueta();
        jPanel1 = new javax.swing.JPanel();
        chkConfigSubTareos = new kevin.component.checkbox.CheckBox();
        jMPanel4 = new views.JMPanel.JMPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        edtIdCultivo = new kevin.component.edittext.EditText();
        edtIdVariedad = new kevin.component.edittext.EditText();
        edtIdLabor = new kevin.component.edittext.EditText();
        edtIdActividad = new kevin.component.edittext.EditText();
        edtIdConsumidor = new kevin.component.edittext.EditText();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo SubTareo");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(principalvalues.colorPrimary);

        btnValidate.setForeground(new java.awt.Color(255, 255, 255));
        btnValidate.setText("close");
        btnValidate.setICO(iconfont.MATERIALICON.MATERIALICONIC.SAVE);
        btnValidate.setICO_color(null);
        btnValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidateActionPerformed(evt);
            }
        });

        etiqueta1.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta1.setText("Nuevo SubTareo");
        etiqueta1.setESITALIC(true);
        etiqueta1.setFONT_SIZE(20.0F);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnValidate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiqueta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnValidate, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        chkConfigSubTareos.setSelected(true);
        chkConfigSubTareos.setText("Respetar Configuracion de SubTareos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 87;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 0, 10);
        jPanel1.add(chkConfigSubTareos, gridBagConstraints);

        jMPanel4.setLayout(new java.awt.BorderLayout());

        jPanel4.setOpaque(false);

        jLabel2.setText("Cultivo");

        jLabel3.setText("Variedad");

        jLabel4.setText("Labor");

        jLabel5.setText("Actividad");

        jLabel6.setText("Consumidor");

        edtIdCultivo.setLabel("Seleccionar Cultivo");
        edtIdCultivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtIdCultivoKeyPressed(evt);
            }
        });

        edtIdVariedad.setLabel("Seleccionar Variedad");

        edtIdLabor.setLabel("Seleccionar Labor");
        edtIdLabor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtIdLaborKeyPressed(evt);
            }
        });

        edtIdActividad.setLabel("Seleccionar Actividad");

        edtIdConsumidor.setLabel("Seleccionar Consumidor");
        edtIdConsumidor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtIdConsumidorKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtIdCultivo, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(edtIdVariedad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edtIdActividad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(edtIdConsumidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtIdLabor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtIdCultivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtIdVariedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtIdLabor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtIdActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtIdConsumidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMPanel4.add(jPanel4, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 11, 10);
        jPanel1.add(jMPanel4, gridBagConstraints);

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidateActionPerformed
        if (edtIdActividad.getText().isEmpty()
                || edtIdLabor.getText().isEmpty()
                || edtIdCultivo.getText().isEmpty()
                || edtIdVariedad.getText().isEmpty()
                || edtIdConsumidor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan algunos Datos...");
        } else {

            NEWITEM = Integer.parseInt(ULTIMOITEM) + 1;

//            Object[] O = TrySQL.exec_procedureReturn(
//                    RunMain.CONECT_JDATA.con,
//                    "exec SetNuevoSubTareo ?,?,?,?,?,?,?,?,?,?;",
//                    new Object[]{
//                        IDTAREO,
//                        jkeys.IDDATABASE,
//                        jkeys.IDEMPRESA,
//                        edtIdCultivo.getText() + "_" + edtIdVariedad.getText(),
//                        JMethods.completar3_item(NEWITEM),
//                        edtIdActividad.getText(),
//                        edtIdLabor.getText(),
//                        edtIdConsumidor.getText(),
//                        ESRENDIM.equals("SI") ? 0 : 1,
//                        ESRENDIM.equals("SI") ? 1 : 0},
//                    4, null);
//            PrintMethods.printer("RESULT: " + Arrays.toString(O));
//            if (O[3].toString().equals("menu_state_ok")) {
//                if (callback != null) {
//                    dispose();
//                    callback.action();
//                }
//
//            } else {
//                JOptionPane.showMessageDialog(this, O[2], "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
//            }
        }
    }//GEN-LAST:event_btnValidateActionPerformed

    private void edtIdCultivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtIdCultivoKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_F2) {
//            JDialog.setDefaultLookAndFeelDecorated(false);
            JMethods.settingGlassPane((JFrame) Frame, buscarCultivoVariedad, principalvalues.colorAccent, 0.6f);
            edtIdCultivo.setText(buscarCultivoVariedad.DATA_SELECT[2].toString());
            edtIdCultivo.setLabel(buscarCultivoVariedad.DATA_SELECT[3].toString());
            edtIdVariedad.setText(buscarCultivoVariedad.DATA_SELECT[4].toString());
            edtIdVariedad.setLabel(buscarCultivoVariedad.DATA_SELECT[5].toString());
//            JDialog.setDefaultLookAndFeelDecorated(true);
        }
    }//GEN-LAST:event_edtIdCultivoKeyPressed

    private void edtIdLaborKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtIdLaborKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_F2) {
            initActividadLabor(edtIdCultivo.getText(), edtIdVariedad.getText());
//            JDialog.setDefaultLookAndFeelDecorated(false);
            JMethods.settingGlassPane((JFrame) Frame, buscarLaborActividad, principalvalues.colorAccent, 0.6f);
            edtIdLabor.setText(buscarLaborActividad.DATA_SELECT[0].toString());
            edtIdLabor.setLabel(buscarLaborActividad.DATA_SELECT[1].toString());
            edtIdActividad.setText(buscarLaborActividad.DATA_SELECT[2].toString());
            edtIdActividad.setLabel(buscarLaborActividad.DATA_SELECT[3].toString());
            ESRENDIM = buscarLaborActividad.DATA_SELECT[4].toString();
//            JDialog.setDefaultLookAndFeelDecorated(true);
        }
    }//GEN-LAST:event_edtIdLaborKeyPressed

    private void edtIdConsumidorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtIdConsumidorKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_F2) {
            initCOnsumidor(edtIdCultivo.getText(), edtIdVariedad.getText(), edtIdActividad.getText(), edtIdLabor.getText());
//            JDialog.setDefaultLookAndFeelDecorated(false);
            JMethods.settingGlassPane((JFrame) Frame, buscarConsumidores, principalvalues.colorAccent, 0.6f);
            edtIdConsumidor.setText(buscarConsumidores.DATA_SELECT[0].toString());
            edtIdConsumidor.setLabel(buscarConsumidores.DATA_SELECT[1].toString());
//            JDialog.setDefaultLookAndFeelDecorated(true);
        }
    }//GEN-LAST:event_edtIdConsumidorKeyPressed

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
            java.util.logging.Logger.getLogger(NuevoSubTareoOnLine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoSubTareoOnLine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoSubTareoOnLine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoSubTareoOnLine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                NuevoSubTareoOnLine dialog = new NuevoSubTareoOnLine(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.ButtonMaterialIcon btnValidate;
    private kevin.component.checkbox.CheckBox chkConfigSubTareos;
    private kevin.component.edittext.EditText edtIdActividad;
    private kevin.component.edittext.EditText edtIdConsumidor;
    private kevin.component.edittext.EditText edtIdCultivo;
    private kevin.component.edittext.EditText edtIdLabor;
    private kevin.component.edittext.EditText edtIdVariedad;
    private kevin.component.label.Etiqueta etiqueta1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private views.JMPanel.JMPanel jMPanel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
