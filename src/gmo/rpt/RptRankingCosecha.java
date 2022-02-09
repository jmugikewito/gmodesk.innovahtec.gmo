package gmo.rpt;

import app.RunMain;
import gmo.methods.jmethods;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import jmugi.model.ImpAdminList;
import jmugi.voids.JMethods;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;
import kevin.component.toast.Toast;

/**

 @author Asus
 */
public class RptRankingCosecha extends javax.swing.JInternalFrame implements ImpAdminList {

    CardLayout cardLayout;
    Window Frame;
    SmartLoader load;
    String IDCULTIVO;
    int EDITNUEVO = 0;

    int countF = 0, countM = 0, countSI = 0, countNO = 0;

    double sumJabas = 0;
    double sumKilos;

    public RptRankingCosecha(Window w) {
        this.Frame = w;
        initComponents();
        cardLayout = (CardLayout) contenedor.getLayout();

        jmethods.cargarCultivos(cboCultivos);
        if (cboCultivos.getItemCount() > 0) {
            cboCultivos.setSelectedIndex(0);
        } else {
            Toast.mostarInfo((JFrame) Frame, "Se necesitan descargar los Cultivos", true);
        }
        panelGraph.setVisible(false);
        goData();

        toolbar.setRECARGAR_CALLBACK(this::gettin_data);
        toolbar.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, "Exportando ..." + getTitle());
        });

    }

    @Override
    public void GetData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Agregar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Editar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Anular() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goFilter() {
        cardLayout.show(contenedor, "cardFilter");
    }

    @Override
    public void goData() {
        panelGraph.setVisible(false);
        cardLayout.show(contenedor, "cardGeneral");
    }

    private void gettin_data() {
        countF = 0;
        countM = 0;
        countNO = 0;
        countSI = 0;
        sumJabas = 0;
        sumJabas = 0;
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos de Ranking",
                "Se estan Descargando los reportes de Ranking de Personal de Mano de Obra...",
                (Window frame) -> {
                    tabla.initHttp("",
                            "Dni, FechaIngreso, Nombres, Sexo, Edad, Jabas, Kilos, PromJabas, PromKilos, Asistencias, Inasistencias, Observado, Motivo, ActivoPlanilla, ActivoCosecha",
                            "Dni, FechaIngreso, Nombres, Sexo, Edad, Jabas, Kilos, PromJabas, PromKilos, Asistencias, Inasistencias, Observado, Motivo, ActivoPlanilla, ActivoCosecha",
                            "Stringx1,DateSQLx1,Stringx2,Integerx1,Doublex4,Integerx2,Stringx4",
                            RunMain.gettin_pages.GetQuery()
                            + "EXEC GetRptRankingCosechaResumen "
                            + "'" + jkeys.IDEMPRESA + "' , '" + IDCULTIVO + "', '', '', '', 0, 0, 0, '','" + cboOrden.getSelectedItem() + " desc', " + Integer.parseInt(edtAsistencias.getText()) + "," + Integer.parseInt(edtJabas.getText()) + "," + Integer.parseInt(edtEdad.getText()) + ",0;"
                    );
                    tabla.GetDatosHTTP();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
        load = null;
        JMethods.updateInternalJTable(this, tabla);

        if (tabla.getRowCount() > 0) {
            panelGraph.setVisible(true);
            jSplitPane1.setDividerLocation(RunMain.SYS_WEIDTH - 360);
            for (int i = 0; i < tabla.getRowCount(); i++) {

                countM = countM + (tabla.getValueAt(i, 3).toString().equals("M") ? 1 : 0);
                countF = countF + (tabla.getValueAt(i, 3).toString().equals("F") ? 1 : 0);
                countSI = countSI + (tabla.getValueAt(i, 13).toString().equals("SI") ? 1 : 0);
                countNO = countNO + (tabla.getValueAt(i, 13).toString().equals("NO") ? 1 : 0);

                sumJabas = sumJabas + Double.parseDouble(tabla.getValueAt(i, 5).toString());
                sumKilos = sumKilos + Double.parseDouble(tabla.getValueAt(i, 6).toString());

            }
            fCantidad.setText(countF + "");
            fPorcentaje.setText(((countF * 100) / tabla.getRowCount()) + "%");
            mCantidad.setText(countM + "");
            mPorcentaje.setText(((countM * 100) / tabla.getRowCount()) + "%");

            siCantidad.setText(countSI + "");
            siPorcentaje.setText(((countSI * 100) / tabla.getRowCount()) + "%");
            noCantidad.setText(countNO + "");
            noPorcentaje.setText(((countNO * 100) / tabla.getRowCount()) + "%");

            etTotalTrabajadores.setText(tabla.getRowCount() + " Trabajadores");
            etTotalJabas.setText("  " + String.format("%.2f", sumJabas) + " Jabas");
            etTotalKilos.setText("   " + String.format("%.2f", sumKilos) + " Kg.");

        } else {
            panelGraph.setVisible(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JPanel();
        panelData = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cboCultivos = new kevin.component.combobox.ComboBox();
        etiqueta1 = new kevin.component.label.Etiqueta();
        cboOrden = new kevin.component.combobox.ComboBox();
        etiqueta2 = new kevin.component.label.Etiqueta();
        edtAsistencias = new kevin.component.edittext.EditText();
        edtJabas = new kevin.component.edittext.EditText();
        edtEdad = new kevin.component.edittext.EditText();
        swiPlanilla = new kevin.component.switchbox.SwitchBox();
        chkPlanilla = new kevin.component.checkbox.CheckBox();
        chkCosecha = new kevin.component.checkbox.CheckBox();
        swiCosecha = new kevin.component.switchbox.SwitchBox();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        panelGraph = new kevin.component.panel.Panel();
        jPanel2 = new javax.swing.JPanel();
        etiqueta3 = new kevin.component.label.Etiqueta();
        fCantidad = new kevin.component.label.Etiqueta();
        fPorcentaje = new kevin.component.label.Etiqueta();
        etiqueta9 = new kevin.component.label.Etiqueta();
        mCantidad = new kevin.component.label.Etiqueta();
        mPorcentaje = new kevin.component.label.Etiqueta();
        jPanel3 = new javax.swing.JPanel();
        etiqueta12 = new kevin.component.label.Etiqueta();
        siCantidad = new kevin.component.label.Etiqueta();
        siPorcentaje = new kevin.component.label.Etiqueta();
        etiqueta15 = new kevin.component.label.Etiqueta();
        noCantidad = new kevin.component.label.Etiqueta();
        noPorcentaje = new kevin.component.label.Etiqueta();
        etTotalTrabajadores = new kevin.component.label.Etiqueta();
        etTotalJabas = new kevin.component.label.Etiqueta();
        etTotalKilos = new kevin.component.label.Etiqueta();
        panelNuevoEditar = new javax.swing.JPanel();
        toolbar = new kevin.component.toolbar.Toolbar();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte de Ranking de Cosecha");

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setLayout(new java.awt.CardLayout());

        panelData.setOpaque(false);
        panelData.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);

        cboCultivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCultivosActionPerformed(evt);
            }
        });

        etiqueta1.setText("Cultivo");

        cboOrden.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombres", "FechaIngreso", "Edad", "Jabas", "Kilos", "Asistencias" }));

        etiqueta2.setText("Ordenar");

        edtAsistencias.setText("0");
        edtAsistencias.setLabel("Minimo Asistencias");
        edtAsistencias.setModo(kevin.component.edittext.EditText.MODO.NUMEROS);

        edtJabas.setText("0");
        edtJabas.setLabel("Minimo Jabas");
        edtJabas.setModo(kevin.component.edittext.EditText.MODO.NUMEROS);

        edtEdad.setText("18");
        edtEdad.setLabel("Minima Edad");
        edtEdad.setModo(kevin.component.edittext.EditText.MODO.NUMEROS);

        swiPlanilla.setEnabled(false);

        chkPlanilla.setText("Activo Planilla");

        chkCosecha.setText("Activo Cosecha");

        swiCosecha.setEnabled(false);

        jSplitPane1.setDividerLocation(850);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Por Sexo"));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(2, 3, 0, 5));

        etiqueta3.setBackground(defaults.colorPrimary);
        etiqueta3.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta3.setText("F");
        etiqueta3.setFONT_SIZE(24.0F);
        etiqueta3.setOpaque(true);
        jPanel2.add(etiqueta3);

        fCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fCantidad.setText("0");
        fCantidad.setFONT_SIZE(18.0F);
        jPanel2.add(fCantidad);

        fPorcentaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fPorcentaje.setText("0%");
        fPorcentaje.setFONT_SIZE(18.0F);
        jPanel2.add(fPorcentaje);

        etiqueta9.setBackground(defaults.colorPrimary);
        etiqueta9.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta9.setText("M");
        etiqueta9.setFONT_SIZE(24.0F);
        etiqueta9.setOpaque(true);
        jPanel2.add(etiqueta9);

        mCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mCantidad.setText("0");
        mCantidad.setFONT_SIZE(18.0F);
        jPanel2.add(mCantidad);

        mPorcentaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mPorcentaje.setText("0%");
        mPorcentaje.setFONT_SIZE(18.0F);
        jPanel2.add(mPorcentaje);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Activos"));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(2, 3, 0, 5));

        etiqueta12.setBackground(defaults.colorPrimary);
        etiqueta12.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta12.setText("SI");
        etiqueta12.setFONT_SIZE(24.0F);
        etiqueta12.setOpaque(true);
        jPanel3.add(etiqueta12);

        siCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        siCantidad.setText("0");
        siCantidad.setFONT_SIZE(18.0F);
        jPanel3.add(siCantidad);

        siPorcentaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        siPorcentaje.setText("0%");
        siPorcentaje.setFONT_SIZE(18.0F);
        jPanel3.add(siPorcentaje);

        etiqueta15.setBackground(defaults.colorPrimary);
        etiqueta15.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta15.setText("NO");
        etiqueta15.setFONT_SIZE(24.0F);
        etiqueta15.setOpaque(true);
        jPanel3.add(etiqueta15);

        noCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noCantidad.setText("0");
        noCantidad.setFONT_SIZE(18.0F);
        jPanel3.add(noCantidad);

        noPorcentaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noPorcentaje.setText("0%");
        noPorcentaje.setFONT_SIZE(18.0F);
        jPanel3.add(noPorcentaje);

        etTotalTrabajadores.setBackground(defaults.colorAccent);
        etTotalTrabajadores.setForeground(new java.awt.Color(255, 255, 255));
        etTotalTrabajadores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etTotalTrabajadores.setText("");
        etTotalTrabajadores.setFONT_SIZE(24.0F);
        etTotalTrabajadores.setOpaque(true);

        etTotalJabas.setBackground(defaults.colorAccent);
        etTotalJabas.setForeground(new java.awt.Color(255, 255, 255));
        etTotalJabas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etTotalJabas.setText("");
        etTotalJabas.setFONT_SIZE(24.0F);
        etTotalJabas.setOpaque(true);

        etTotalKilos.setBackground(defaults.colorAccent);
        etTotalKilos.setForeground(new java.awt.Color(255, 255, 255));
        etTotalKilos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etTotalKilos.setText("");
        etTotalKilos.setFONT_SIZE(24.0F);
        etTotalKilos.setOpaque(true);

        javax.swing.GroupLayout panelGraphLayout = new javax.swing.GroupLayout(panelGraph);
        panelGraph.setLayout(panelGraphLayout);
        panelGraphLayout.setHorizontalGroup(
            panelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraphLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(etTotalTrabajadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(etTotalJabas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(etTotalKilos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelGraphLayout.setVerticalGroup(
            panelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGraphLayout.createSequentialGroup()
                .addComponent(etTotalTrabajadores, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etTotalJabas, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etTotalKilos, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(panelGraph);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSplitPane1)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboCultivos, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(etiqueta2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(edtAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtJabas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(chkPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chkCosecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(swiPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(swiCosecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(etiqueta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboCultivos, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(etiqueta2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtJabas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkCosecha, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(swiPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(swiCosecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        panelData.add(jPanel1, java.awt.BorderLayout.CENTER);

        contenedor.add(panelData, "cardGeneral");

        panelNuevoEditar.setOpaque(false);

        javax.swing.GroupLayout panelNuevoEditarLayout = new javax.swing.GroupLayout(panelNuevoEditar);
        panelNuevoEditar.setLayout(panelNuevoEditarLayout);
        panelNuevoEditarLayout.setHorizontalGroup(
            panelNuevoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        panelNuevoEditarLayout.setVerticalGroup(
            panelNuevoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
        );

        contenedor.add(panelNuevoEditar, "cardFilter");

        getContentPane().add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar.setBackground(new java.awt.Color(255, 255, 255));
        toolbar.setAGREGAR(false);
        toolbar.setCHART(true);
        toolbar.setEDITAR(false);
        toolbar.setELIMINAR(false);
        toolbar.setEXCEL(true);
        toolbar.setFILTRAR(true);
        toolbar.setIMPORTAR(false);
        toolbar.setIMPRIMIR(false);
        toolbar.setINDICADOR(false);
        toolbar.setIREPORT(false);
        toolbar.setRECARGAR(true);
        toolbar.setOpaque(true);
        toolbar.setPreferredSize(new java.awt.Dimension(42, 240));
        getContentPane().add(toolbar, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboCultivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCultivosActionPerformed
    }//GEN-LAST:event_cboCultivosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.combobox.ComboBox cboCultivos;
    private kevin.component.combobox.ComboBox cboOrden;
    private kevin.component.checkbox.CheckBox chkCosecha;
    private kevin.component.checkbox.CheckBox chkPlanilla;
    private javax.swing.JPanel contenedor;
    private kevin.component.edittext.EditText edtAsistencias;
    private kevin.component.edittext.EditText edtEdad;
    private kevin.component.edittext.EditText edtJabas;
    private kevin.component.label.Etiqueta etTotalJabas;
    private kevin.component.label.Etiqueta etTotalKilos;
    private kevin.component.label.Etiqueta etTotalTrabajadores;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta12;
    private kevin.component.label.Etiqueta etiqueta15;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private kevin.component.label.Etiqueta etiqueta9;
    private kevin.component.label.Etiqueta fCantidad;
    private kevin.component.label.Etiqueta fPorcentaje;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private kevin.component.label.Etiqueta mCantidad;
    private kevin.component.label.Etiqueta mPorcentaje;
    private kevin.component.label.Etiqueta noCantidad;
    private kevin.component.label.Etiqueta noPorcentaje;
    private javax.swing.JPanel panelData;
    private kevin.component.panel.Panel panelGraph;
    private javax.swing.JPanel panelNuevoEditar;
    private kevin.component.label.Etiqueta siCantidad;
    private kevin.component.label.Etiqueta siPorcentaje;
    private kevin.component.switchbox.SwitchBox swiCosecha;
    private kevin.component.switchbox.SwitchBox swiPlanilla;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void Guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Cancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
