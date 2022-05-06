package gmo.rpt;

import app.RunMain;
import color.MaterialColor;
import conexion.Jdata_Conexion;
import gmo.methods.jmethods;
import gmo.utils.jkeys;
import gmo.utils.jvalues;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import jmugi.voids.DateTimeUtil;
import jmugi.voids.JMethods;
import jmugi.voids.PrintMethods;
import jmugi.voids.gmoEncript2022;
import jmugi.voids.gmoEncript2022;
import kevin.component.defaults;
import kevin.component.dialog.MaterialSmartDialog;
import kevin.component.dialog.SmartLoader;
import kevin.component.frame.GMOInternalFrame;
import utils.ExecHTTP;

public class RptDataTareo extends GMOInternalFrame {

    String IDUSER = "", IDACTIVIDAD = "", IDLABOR = "";
    JInternalFrame internal;
    int col_data, ORDER = 0;

    public RptDataTareo(Window w) {
        this.Frame = w;
        initComponents();
        internal = this;
        tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jmethods.cargarUsuarios(cboUsuario);

        toolbar1.setRECARGAR_CALLBACK(this::gettin_dataHorarios);
//        toolbar1.setRECARGAR_CALLBACK(this::gettin_data);

        toolbar1.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, getTitle());
        });

//        FixedColumnTable fixed = new FixedColumnTable(4, jScrollPane2);
        menuReportes.setVisible(false);
        jmethods.cargarPlanillas(cboPlanillas, " 'u','8' ", jvalues.USUARIO.getFirma().length() > 0 ? gmoEncript2022.encriptar(JMethods.splitStringComa(jvalues.USUARIO.getFirma())) : "''");

        lblPlanilla.setVisible(cboDestino.getSelectedIndex() == 3);
        cboPlanillas.setVisible(cboDestino.getSelectedIndex() == 3);
    }

    public RptDataTareo(Window w, String idtrabajador, String fecha1, String fecha2) {
        this.Frame = w;
        initComponents();
        internal = this;

        PrintMethods.printer("INPUT FILTERING: " + idtrabajador + "\t" + fecha1 + "\t" + fecha2);
        tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jmethods.cargarUsuarios(cboUsuario);

        toolbar1.setRECARGAR_CALLBACK(this::gettin_dataHorarios);
//        toolbar1.setRECARGAR_CALLBACK(this::gettin_data);
        toolbar1.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, getTitle());
        });
        edtDni.setText(idtrabajador);
        chooserDate1.setDate(DateTimeUtil.StringToDate(fecha1));
        chooserDate2.setDate(DateTimeUtil.StringToDate(fecha2));
        menuReportes.setVisible(false);

        JMethods.actionBackend(this::gettin_dataHorarios, 1200);
//        JMethods.actionBackend(this::gettin_data, 1200);
        jmethods.cargarPlanillas(cboPlanillas, " 'u','8' ", jvalues.USUARIO.getFirma().length() > 0 ? gmoEncript2022.encriptar(JMethods.splitStringComa(jvalues.USUARIO.getFirma())) : "''");
    }

    private void generarExcelDirecto(int rpt) {
//        if (RunMain.CONECT_JDATA == null) {
//            MaterialSmartDialog.showConfirmation(Frame, "Antes de Descargar el reporte necesitamos Conectar con el Servidor, esta seguro de Conectar??? ",
//                    () -> {
//                        RunMain.CONECT_JDATA = new Jdata_Conexion(Frame);
//                    },
//                    null
//            );
//        } else {
//            tabla.exportExcel(RunMain.CONECT_JDATA.con,
//                    (JFrame) Frame,
//                    "Exportando Reporte - " + getTitle(),
//                    ExecHTTP.parseQuery("exec GetListDataTareoSwift ?1 , ?2 , ?3 , ?4 , ?5 , ?6 , ?7 , ?8 , ?9 ;", false,
//                            jkeys.IDDATABASE,
//                            jkeys.IDEMPRESA,
//                            chooserDate1.toStringDate(),
//                            chooserDate2.toStringDate(),
//                            "",
//                            rpt,
//                            edtDni.getText(),
//                            IDACTIVIDAD,
//                            IDLABOR
//                    ), true);
//        }
    }

    private void gettin_data() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos",
                "Actualizando Datos Exportados de los Tareos del " + chooserDate1.toStringDate() + " hacia " + chooserDate2.toStringDate(), (Window frame) -> {
            tabla.initHttp("0,90,0,110,90,360,0,0,0,0,90,200,90,200,90,200,60,120,80,80,80,80,80,80,160",
                    "idtareo, fecha, numero, idusuario, dni, nombres, itemid, item, hora_inicio, hora_fin, idactividad, actividad, idlabor, labor, idconsumidor, consumidor, idmotivo, motivo, JOR, JOREX, JOR_NOC, JORNAL, RENDIM, AVANCE, observaciones",
                    "idtareo, fecha, numero, idusuario, dni, nombres, itemid, item, hora_inicio, hora_fin, idactividad, actividad, idlabor, labor, idconsumidor, consumidor, idmotivo, motivo, JOR, JOREX, JOR_NOC, JORNAL, RENDIM, AVANCE, observaciones",
                    "Stringx1,DateSQLx1,Stringx16,Doublex6,Stringx1",
                    RunMain.gettin_pages.api_get()
                    + ExecHTTP.parseQL("exec GetListDataTareo ",
                            jkeys.IDDATABASE,
                            jkeys.IDEMPRESA,
                            chooserDate1.toStringDate(),
                            chooserDate2.toStringDate(),
                            edtUsuario.getText(),
                            0,
                            edtDni.getText(),
                            cboDestino.getSelectedIndex(),
                            cboPlanillas.getIditem(),
                            jkeys.IDUSUARIO
                    )
            );
            tabla.GetDatosHTTP2022();
            col_data = 17;
            JMethods.updateInternalJTable(this, tabla);
            tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatDataTareo());
            JMethods.updateInternalJTable(internal, tabla);
            load.dispose();
            JDialog.setDefaultLookAndFeelDecorated(true);
        });
        JMethods.settingGlassPane((JFrame) Frame, load, MaterialColor.BLUEGREY_900, 0.8f);
        load = null;
    }

    private void gettin_dataHorarios() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos",
                "Actualizando Datos Exportados de los Tareos del " + chooserDate1.toStringDate() + " hacia " + chooserDate2.toStringDate(), (Window frame) -> {
            tabla.initHttp("0,100,100,150,100,200,100,100,200,80,200,80,80,100,100,100,100,100,100,150,100,150,100,150,100,100,100,100,100,100,100,100,100,120",
                    "idtareo, fecha, numero, idusuario, dnisupervisor, supervisor, codigosap, dni, nombres, sexo, cargo, itemid, item, hora_inicio,ref_inicio,ref_fin, hora_fin, horas_cal, idactividad, actividad, idlabor, labor, idconsumidor, consumidor, idmotivo, motivo, JOR, JOREX, JOR_NOC, JORNAL, JOR_COMP, RENDIM, AVANCE, observaciones",
                    "idtareo, fecha, numero, idusuario, dnisupervisor, supervisor, codigosap, dni, nombres, sexo, cargo, itemid, item, hora_inicio,ref_inicio,ref_fin, hora_fin, horas_cal, idactividad, actividad, idlabor, labor, idconsumidor, consumidor, idmotivo, motivo, JOR, JOREX, JOR_NOC, JORNAL, JOR_COMP, RENDIM, AVANCE, observaciones",
                    "Stringx1,DateSQLx1,Stringx24,Doublex7,Stringx1",
                    RunMain.gettin_pages.api_get()
                    + ExecHTTP.parseQL("exec GetListDataTareo2022 ",
                            jkeys.IDDATABASE,
                            jkeys.IDEMPRESA,
                            chooserDate1.toStringDate(),
                            chooserDate2.toStringDate(),
                            edtUsuario.getText(),
                            0,
                            edtDni.getText(),
                            cboDestino.getSelectedIndex(),
                            cboPlanillas.getIditem(),
                            jkeys.IDUSUARIO
                    )
            );
            tabla.GetDatosHTTP2022();
            col_data = 17;
            JMethods.updateInternalJTable(this, tabla);
            tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatDataTareo());
            JMethods.updateInternalJTable(internal, tabla);
            load.dispose();
            JDialog.setDefaultLookAndFeelDecorated(true);
        });
        JMethods.settingGlassPane((JFrame) Frame, load, MaterialColor.BLUEGREY_900, 0.8f);
        load = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiqueta4 = new kevin.component.label.Etiqueta();
        buttonGroup1 = new javax.swing.ButtonGroup();
        etiqueta3 = new kevin.component.label.Etiqueta();
        cboUsuario = new kevin.component.combobox.ComboBox();
        jPanel1 = new javax.swing.JPanel();
        toolbar1 = new kevin.component.toolbar.Toolbar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        panel1 = new kevin.component.panel.Panel();
        etiqueta1 = new kevin.component.label.Etiqueta();
        chooserDate1 = new kevin.component.date.MaterialDateChooser();
        etiqueta2 = new kevin.component.label.Etiqueta();
        chooserDate2 = new kevin.component.date.MaterialDateChooser();
        etiqueta5 = new kevin.component.label.Etiqueta();
        edtUsuario = new kevin.component.edittext.EditText();
        buttonMaterialIcon2 = new kevin.component.button.ButtonMaterialIcon();
        edtDni = new kevin.component.edittext.EditText();
        buttonMaterialIcon1 = new kevin.component.button.ButtonMaterialIcon();
        etiquetaSmart1 = new kevin.component.label.EtiquetaSmart();
        cboDestino = new kevin.component.combobox.ComboBox();
        lblPlanilla = new javax.swing.JLabel();
        cboPlanillas = new kevin.component.combobox.ComboBox();
        menuReportes = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        men_datosgenerales = new kevin.component.menu.GMOMenuItem();
        men_datosconsupervisor = new kevin.component.menu.GMOMenuItem();
        men_data_auditoria = new kevin.component.menu.GMOMenuItem();

        etiqueta4.setText("ERP Externo");

        etiqueta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta3.setText("Usuario");

        cboUsuario.setMaximumSize(new java.awt.Dimension(360, 18));
        cboUsuario.setMinimumSize(new java.awt.Dimension(240, 18));
        cboUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuarioActionPerformed(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte General de Datos de Tareos");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        toolbar1.setCHART(false);
        toolbar1.setENVIAR(false);
        toolbar1.setEXCEL(true);
        toolbar1.setIMPRIMIR(false);
        toolbar1.setINDICADOR(false);
        toolbar1.setMAIL(false);
        toolbar1.setPDF(false);
        toolbar1.setRECARGAR(true);
        jPanel1.add(toolbar1, java.awt.BorderLayout.WEST);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 691));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setIsAutomaticResize(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);

        etiqueta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta1.setText("Inicio");
        panel1.add(etiqueta1);

        chooserDate1.setPreferredSize(new java.awt.Dimension(132, 32));
        panel1.add(chooserDate1);

        etiqueta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta2.setText("Fin");
        panel1.add(etiqueta2);

        chooserDate2.setPreferredSize(new java.awt.Dimension(132, 32));
        panel1.add(chooserDate2);

        etiqueta5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta5.setText("   ");
        panel1.add(etiqueta5);

        edtUsuario.setDigit_limit(8);
        edtUsuario.setLabel("Codigo Usuario");
        edtUsuario.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_CANTIDAD);
        edtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtUsuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edtUsuarioKeyTyped(evt);
            }
        });
        panel1.add(edtUsuario);

        buttonMaterialIcon2.setText("buttonMaterialIcon1");
        buttonMaterialIcon2.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon2.setICO_size(18.0F);
        buttonMaterialIcon2.setPreferredSize(new java.awt.Dimension(32, 32));
        buttonMaterialIcon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon2ActionPerformed(evt);
            }
        });
        panel1.add(buttonMaterialIcon2);

        edtDni.setDigit_limit(8);
        edtDni.setLabel("Dni Trabajador");
        edtDni.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_CANTIDAD);
        edtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edtDniKeyTyped(evt);
            }
        });
        panel1.add(edtDni);

        buttonMaterialIcon1.setText("buttonMaterialIcon1");
        buttonMaterialIcon1.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon1.setICO_size(18.0F);
        buttonMaterialIcon1.setPreferredSize(new java.awt.Dimension(32, 32));
        buttonMaterialIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon1ActionPerformed(evt);
            }
        });
        panel1.add(buttonMaterialIcon1);

        etiquetaSmart1.setText("Destino de Trabajo");
        etiquetaSmart1.setESITALIC(false);
        etiquetaSmart1.setFONT(defaults.FUENTE);
        etiquetaSmart1.setFONT_SIZE(13.0F);
        etiquetaSmart1.setLineWrap(true);
        etiquetaSmart1.setPreferredSize(new java.awt.Dimension(78, 34));
        panel1.add(etiquetaSmart1);

        cboDestino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CAMPO", "PACKING", "TODOS", "POR PLANILLA" }));
        cboDestino.setPreferredSize(new java.awt.Dimension(140, 34));
        cboDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDestinoActionPerformed(evt);
            }
        });
        panel1.add(cboDestino);

        lblPlanilla.setText("  Planilla");
        panel1.add(lblPlanilla);

        cboPlanillas.setPreferredSize(new java.awt.Dimension(240, 34));
        panel1.add(cboPlanillas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1148, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Reportes");

        men_datosgenerales.setText("Datos Generales");
        men_datosgenerales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                men_datosgeneralesActionPerformed(evt);
            }
        });
        jMenu1.add(men_datosgenerales);

        men_datosconsupervisor.setText("Datos Generales con Supervisor");
        men_datosconsupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                men_datosconsupervisorActionPerformed(evt);
            }
        });
        jMenu1.add(men_datosconsupervisor);

        men_data_auditoria.setText("Datos Generales Auditable");
        men_data_auditoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                men_data_auditoriaActionPerformed(evt);
            }
        });
        jMenu1.add(men_data_auditoria);

        menuReportes.add(jMenu1);

        setJMenuBar(menuReportes);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuarioActionPerformed
        IDUSER = cboUsuario.DATA.get(cboUsuario.getSelectedIndex())[0].toString();
        PrintMethods.printer("USUARIO SELECCIONADO: " + IDUSER);
        gettin_dataHorarios();
//        gettin_data();
    }//GEN-LAST:event_cboUsuarioActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
    }//GEN-LAST:event_tablaMouseClicked

    private void buttonMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon1ActionPerformed
        edtDni.setText("");
    }//GEN-LAST:event_buttonMaterialIcon1ActionPerformed

    private void edtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDniKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_edtDniKeyTyped

    private void edtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDniKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && edtDni.getText().length() == 8)
            gettin_dataHorarios();
//            gettin_data();
    }//GEN-LAST:event_edtDniKeyPressed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void men_datosgeneralesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_datosgeneralesActionPerformed
        generarExcelDirecto(0);
    }//GEN-LAST:event_men_datosgeneralesActionPerformed

    private void men_datosconsupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_datosconsupervisorActionPerformed
        generarExcelDirecto(1);
    }//GEN-LAST:event_men_datosconsupervisorActionPerformed

    private void edtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtUsuarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtUsuarioKeyPressed

    private void edtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_edtUsuarioKeyTyped

    private void buttonMaterialIcon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon2ActionPerformed
        edtUsuario.setText("");
    }//GEN-LAST:event_buttonMaterialIcon2ActionPerformed

    private void cboDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDestinoActionPerformed
        lblPlanilla.setVisible(cboDestino.getSelectedIndex() == 3);
        cboPlanillas.setVisible(cboDestino.getSelectedIndex() == 3);
    }//GEN-LAST:event_cboDestinoActionPerformed

    private void men_data_auditoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_data_auditoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_men_data_auditoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon1;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon2;
    private kevin.component.combobox.ComboBox cboDestino;
    private kevin.component.combobox.ComboBox cboPlanillas;
    private kevin.component.combobox.ComboBox cboUsuario;
    private kevin.component.date.MaterialDateChooser chooserDate1;
    private kevin.component.date.MaterialDateChooser chooserDate2;
    private kevin.component.edittext.EditText edtDni;
    private kevin.component.edittext.EditText edtUsuario;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private kevin.component.label.Etiqueta etiqueta4;
    private kevin.component.label.Etiqueta etiqueta5;
    private kevin.component.label.EtiquetaSmart etiquetaSmart1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPlanilla;
    private kevin.component.menu.GMOMenuItem men_data_auditoria;
    private kevin.component.menu.GMOMenuItem men_datosconsupervisor;
    private kevin.component.menu.GMOMenuItem men_datosgenerales;
    private javax.swing.JMenuBar menuReportes;
    private kevin.component.panel.Panel panel1;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables
}

class FormatDataTareo extends DefaultTableCellRenderer {

    public FormatDataTareo() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setEnabled(table == null || table.isEnabled());
        switch (column) {
            case 18:
            case 19:
            case 20:
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                setBackground(Color.WHITE);
                setForeground(defaults.colorPrimaryDark);
                break;
            case 26:
            case 28:
            case 29:
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                setBackground(defaults.colorPrimary);
                setForeground(Color.WHITE);
                break;
            case 30:
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                setBackground(Color.ORANGE);
                setForeground(Color.WHITE);
                break;
            default:
                setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
                break;
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        return this;
    }
}
