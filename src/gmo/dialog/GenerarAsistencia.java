package gmo.dialog;

import app.RunMain;
import color.MaterialColor;
import static gmo.core.MainLite.*;
import gmo.utils.jkeys;
import iconfont.MATERIALICON;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import jmugi.voids.JMethods;
import jmugi.voids.JOptionPane_methods;
import kevin.component.defaults;
import utils.ExecHTTP;

/**

 @author jmugi
 */
public class GenerarAsistencia extends javax.swing.JDialog {

    /**
     Creates new form Dialog_frm_GenerarAsistencia
     */
    JFrame frame;
    String IDTAREO;
    Object[][] DATA;
    boolean TODOBIEN = false;

    public GenerarAsistencia(JFrame parent, boolean modal) {
        super(parent, modal);
        this.frame = parent;
        initComponents();
        btn_Done.setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.DONE, MaterialColor.WHITE, 56));
    }

    public GenerarAsistencia(JFrame parent, boolean modal, String idtareo) {
        super(parent, modal);
        this.frame = parent;
        this.IDTAREO = idtareo;
        initComponents();
        JDialog.setDefaultLookAndFeelDecorated(true);
        btn_Done.setVisible(false);
    }

    public void gettin_data() {

        tabla.initHttp("",
                "xITEM,IDCODIGOGENERAL,NOMBRECOMPLETO,MENSAJE,vienedecruce,CANTMARCA,IDEMPRESA,IDTAREO,IDCODIGOGENERAL,TOTALHORASTAREO,MINIMAMARCA,MAXMARCA,DIFHORAMARCA,VIENEDECRUCE,MENSAJE,TIPOERROR",
                "xITEM,IDCODIGOGENERAL,NOMBRECOMPLETO,MENSAJE,vienedecruce,CANTMARCA,IDEMPRESA,IDTAREO,IDCODIGOGENERAL,TOTALHORASTAREO,MINIMAMARCA,MAXMARCA,DIFHORAMARCA,VIENEDECRUCE,MENSAJE,TIPOERROR",
                "tipes",
                gettin_pages.api_get() + "exec sptareo_getValidacionGenerarAsistencia '" + jkeys.IDEMPRESA + "','" + IDTAREO + "';"
        );
        tabla.GetDatosHTTP();

        JMethods.updateWindowJTable(this, tabla);

        DATA = tabla.getDATA();
        tabla.updateUI();
        tabla.repaint();
        if (tabla.getRowCount() == 0) {
            btn_Done.setICO(iconfont.MATERIALICON.MATERIALICONIC.DONE);
            btn_Done.setToolTipText("GENERAR ASISTENCIA");
            JOptionPane.showMessageDialog(frame, " Todo bien, podemos continuar creando la Asistencia ");
            TODOBIEN = true;
        } else {
            btn_Done.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
            btn_Done.setToolTipText("CERRAR");
            JOptionPane.showMessageDialog(frame, " Se encontraron algunas Observaciones resolverlas y volver a consultar");
            TODOBIEN = false;
        }
        btn_Done.setVisible(TODOBIEN);
    }

    public void gettin_dataDone() {
        tabla.initHttp("0",
                "ITEM;IDASISTENCIA,DOC,SERIE,NUMERO,IDPLANILLA,PLANILLA,SEMANA,IDESTADO,FECHA,REND,IDTURNO,TURNO",
                "ITEM;IDASISTENCIA,DOC,SERIE,NUMERO,IDPLANILLA,PLANILLA,SEMANA,IDESTADO,FECHA,REND,IDTURNO,TURNO",
                "Stringx10",
                gettin_pages.api_get() + ExecHTTP.parseQL("exec sptareo_getAsistenciaGeneradabyTareo ", jkeys.IDEMPRESA, IDTAREO)
        );
        tabla.GetDatosHTTP();

        JMethods.updateWindowJTable(this, tabla);
        DATA = tabla.getDATA();
        tabla.updateUI();
        tabla.repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jmugiLabelNormal1 = new views.JMLabel.JmugiLabelNormal();
        btn_Done = new kevin.component.button.ButtonMaterialIcon();
        btnClose = new kevin.component.button.ButtonMaterialIcon();
        btn_Consultar = new kevin.component.button.ButtonMaterialIcon();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generando Asistencia");
        setModal(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(defaults.colorPrimary);

        jmugiLabelNormal1.setForeground(new java.awt.Color(255, 255, 255));
        jmugiLabelNormal1.setText("Generar Asistencia");
        jmugiLabelNormal1.setESITALIC(true);
        jmugiLabelNormal1.setFONT_SIZE(28.0F);

        btn_Done.setText("buttonMaterialIcon1");
        btn_Done.setToolTipText("Cerrar");
        btn_Done.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        btn_Done.setICO_color(java.awt.Color.white);
        btn_Done.setICO_size(32.0F);
        btn_Done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DoneActionPerformed(evt);
            }
        });

        btnClose.setText("buttonMaterialIcon1");
        btnClose.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        btnClose.setICO_color(new java.awt.Color(204, 204, 255));
        btnClose.setICO_size(24.0F);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btn_Consultar.setText("buttonMaterialIcon1");
        btn_Consultar.setToolTipText("Validar Generacion de Asistencia");
        btn_Consultar.setICO(iconfont.MATERIALICON.MATERIALICONIC.ROTATE_RIGHT);
        btn_Consultar.setICO_color(null);
        btn_Consultar.setICO_size(32.0F);
        btn_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Done, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jmugiLabelNormal1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_Consultar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jmugiLabelNormal1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClose, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btn_Done, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_DoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DoneActionPerformed
        if (TODOBIEN) {
            ExecHTTP.ExecPostProcedure(frame,
                    gettin_pages.api_set(),
                    new String[]{"iddatabase2", "query"},
                    new Object[]{
                        jkeys.IDDATABASE2,
                        ExecHTTP.parseQL("exec " + jkeys.IDDATABASE + "..generarasistencia_bytareo ", jkeys.IDEMPRESA, IDTAREO.trim())
                    },
                    () -> {//ACTION DONE
                        btn_Done.setVisible(false);
                        btn_Consultar.setVisible(false);
                        gettin_dataDone();
                    },
                    () -> {//ACTION WARN
                    });
        } else {
            JOptionPane_methods.MostrarConfirmacion(frame, (Window frame1) -> {
                dispose();
            }, "Se ha encontrado Observaciones, se cerrara el Formulario, esta Seguro?");
        }
    }//GEN-LAST:event_btn_DoneActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        gettin_data();
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    /**
     @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerarAsistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            GenerarAsistencia dialog = new GenerarAsistencia(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.ButtonMaterialIcon btnClose;
    private kevin.component.button.ButtonMaterialIcon btn_Consultar;
    private kevin.component.button.ButtonMaterialIcon btn_Done;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private views.JMLabel.JmugiLabelNormal jmugiLabelNormal1;
    private kevin.component.tabla.TablaSmart tabla;
    // End of variables declaration//GEN-END:variables
}
