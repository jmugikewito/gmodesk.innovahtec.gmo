package gmo.internal;

import color.MaterialColor;
import static gmo.core.MainLite.gettin_pages;
import gmo.utils.jkeys;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import jmugi.model.Acceso;
import jmugi.model.iKubeForm;
import jmugi.voids.JMethods;
import kevin.component.dialog.SmartLoader;
import utils.ExecHTTP;

/**

 @author Miguel
 */
public class AdminMotivos extends javax.swing.JInternalFrame implements iKubeForm {

    Window FRAME;
    int posi = 0;
    SmartLoader load;
    JInternalFrame internal;

    public AdminMotivos(Window frame, Acceso acceso) {
        initComponents();
        this.FRAME = frame;
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> IMPRIMIENDO ACCESO >>>>>>>>>>>>>>>>>>>>>>>>");
        toolbar1.setAcceso(acceso);

        toolbar1.setRECARGAR_CALLBACK(() -> {
            Consultar();
        });

        toolbar1.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) FRAME, getTitle());
        });

        toolbar1.setANULAR_CALLBACK(() -> {
            Anular();
        });
        toolbar1.setELIMINAR_CALLBACK(() -> {
            Eliminar();
        });
    }

    @Override
    public void Consultar() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) FRAME, true,
                "Descargando Datos: " + getTitle(),
                "Se estan Descargando los Datos de Motivos",
                (Window frame) -> {
//                    tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatCIS());
//                    tabla.loadApiDataSmart(
//                            "listar-asistencias",
//                            "idempresa,fecha1,fecha2,tipo,idtrabajador,activo,packing",
//                            jkeys.IDEMPRESA,
//                    );
                    tabla.GetDatosHTTP_2022(
                            gettin_pages.api_getgg() + ExecHTTP.parseQL(
                            "EXEC GetListMotivos", 1, "ADMIN", jkeys.IDDATABASE
                    ));
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) FRAME, load, MaterialColor.BLUEGREY_900, 0.8f);
        load = null;
        JMethods.updateInternalJTable(this, tabla);
    }

    @Override
    public void Anular() {

    }

    @Override
    public void Guardar() {

    }

    @Override
    public void Eliminar() {

    }

    @Override
    public void Editar() {

    }

    @Override
    public void Agregar() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        contenedor = new javax.swing.JPanel();
        panelData = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        panelNuevoEditar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        edtIdMotivo = new kevin.component.edittext.EditText();
        edtNombreCorto = new kevin.component.edittext.EditText();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        edtObservaciones = new kevin.component.textarea.TextArea();
        jPanel2 = new javax.swing.JPanel();
        chkTareo = new kevin.component.checkbox.CheckBox();
        chkPreTareo = new kevin.component.checkbox.CheckBox();
        chkCosecha = new kevin.component.checkbox.CheckBox();
        chkProgramacion = new kevin.component.checkbox.CheckBox();
        chkAsisPerson = new kevin.component.checkbox.CheckBox();
        chkPacking = new kevin.component.checkbox.CheckBox();
        chkRecepcion = new kevin.component.checkbox.CheckBox();
        chkDespacho = new kevin.component.checkbox.CheckBox();
        chkRiego = new kevin.component.checkbox.CheckBox();
        chkFSanidad = new kevin.component.checkbox.CheckBox();
        chkCntProceso = new kevin.component.checkbox.CheckBox();
        chkMaquinaria = new kevin.component.checkbox.CheckBox();
        chkCostos = new kevin.component.checkbox.CheckBox();
        chkCntrEstados = new kevin.component.checkbox.CheckBox();
        chkAsisTransp = new kevin.component.checkbox.CheckBox();
        edtDescripcionn = new kevin.component.edittext.EditText();
        jPanel3 = new javax.swing.JPanel();
        chkAlerta = new kevin.component.checkbox.CheckBox();
        chkAviso = new kevin.component.checkbox.CheckBox();
        chkCtrlBoletas = new kevin.component.checkbox.CheckBox();
        chkActivo = new kevin.component.checkbox.CheckBox();
        chkParaDocID = new kevin.component.checkbox.CheckBox();
        comboBox1 = new kevin.component.combobox.ComboBox();
        jLabel1 = new javax.swing.JLabel();
        panelImportar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaImport = new kevin.component.tabla.TablaSmart();
        toolbar1 = new kevin.component.toolbar.Toolbar();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Administracion de Motivos GMO");

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setLayout(new java.awt.CardLayout());

        panelData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        panelData.setOpaque(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setIsBooleanColumn(true);
        jScrollPane.setViewportView(tabla);

        javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
        panelData.setLayout(panelDataLayout);
        panelDataLayout.setHorizontalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDataLayout.setVerticalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(panelData, "cardGeneral");

        panelNuevoEditar.setOpaque(false);
        panelNuevoEditar.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        edtIdMotivo.setDigit_limit(11);
        edtIdMotivo.setLabel("ID Motivo");
        edtIdMotivo.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_CANTIDAD);

        edtNombreCorto.setDigit_limit(11);
        edtNombreCorto.setLabel("Nombre Corto");
        edtNombreCorto.setModo(kevin.component.edittext.EditText.MODO.NORMAL);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Observaciones:");

        jScrollPane4.setViewportView(edtObservaciones);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Controles"));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(5, 4, 5, 5));

        chkTareo.setSelected(true);
        chkTareo.setText("Tareo");
        jPanel2.add(chkTareo);

        chkPreTareo.setText("PreTareo");
        jPanel2.add(chkPreTareo);

        chkCosecha.setText("Cosecha");
        jPanel2.add(chkCosecha);

        chkProgramacion.setText("Programacion");
        jPanel2.add(chkProgramacion);

        chkAsisPerson.setText("Asis Personal");
        jPanel2.add(chkAsisPerson);

        chkPacking.setText("Packing");
        jPanel2.add(chkPacking);

        chkRecepcion.setText("Recepcion");
        jPanel2.add(chkRecepcion);

        chkDespacho.setText("Despacho");
        jPanel2.add(chkDespacho);

        chkRiego.setText("Riego");
        jPanel2.add(chkRiego);

        chkFSanidad.setText("FitoSanidad");
        jPanel2.add(chkFSanidad);

        chkCntProceso.setText("Control de Procesos");
        jPanel2.add(chkCntProceso);

        chkMaquinaria.setText("Maquinaria");
        jPanel2.add(chkMaquinaria);

        chkCostos.setText("Costos");
        jPanel2.add(chkCostos);

        chkCntrEstados.setText("Control de Estados");
        jPanel2.add(chkCntrEstados);

        chkAsisTransp.setText("Asis Transportistas");
        jPanel2.add(chkAsisTransp);

        edtDescripcionn.setDigit_limit(11);
        edtDescripcionn.setLabel("Descripcion");
        edtDescripcionn.setModo(kevin.component.edittext.EditText.MODO.NORMAL);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Otros"));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        chkAlerta.setText("Envia Alertas");
        jPanel3.add(chkAlerta);

        chkAviso.setText("Envia Avisos");
        jPanel3.add(chkAviso);

        chkCtrlBoletas.setText("Impresion Boletas");
        jPanel3.add(chkCtrlBoletas);

        chkActivo.setSelected(true);
        chkActivo.setText("Activo");
        jPanel3.add(chkActivo);

        chkParaDocID.setText("para Docum. Identidad");
        jPanel3.add(chkParaDocID);

        jLabel1.setText("Tipo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(edtIdMotivo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(edtNombreCorto, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(edtDescripcionn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(comboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edtIdMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtNombreCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtDescripcionn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 184;
        gridBagConstraints.ipady = 115;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 59, 124, 93);
        panelNuevoEditar.add(jPanel1, gridBagConstraints);

        contenedor.add(panelNuevoEditar, "cardFilter");

        panelImportar.setOpaque(false);

        tablaImport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaImport.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        jScrollPane2.setViewportView(tablaImport);

        javax.swing.GroupLayout panelImportarLayout = new javax.swing.GroupLayout(panelImportar);
        panelImportar.setLayout(panelImportarLayout);
        panelImportarLayout.setHorizontalGroup(
            panelImportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImportarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        panelImportarLayout.setVerticalGroup(
            panelImportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImportarLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        contenedor.add(panelImportar, "cardImportar");

        getContentPane().add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar1.setAGREGAR(true);
        toolbar1.setANULAR(true);
        toolbar1.setEDITAR(true);
        toolbar1.setELIMINAR(true);
        toolbar1.setENVIAR(false);
        toolbar1.setEXCEL(false);
        toolbar1.setFILTRAR(false);
        toolbar1.setGUARDAR(false);
        toolbar1.setHISTORIAL(false);
        toolbar1.setIMPORTAR(false);
        toolbar1.setIMPRIMIR(false);
        toolbar1.setINDICADOR(false);
        toolbar1.setIREPORT(false);
        toolbar1.setRECARGAR(true);
        getContentPane().add(toolbar1, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.checkbox.CheckBox chkActivo;
    private kevin.component.checkbox.CheckBox chkAlerta;
    private kevin.component.checkbox.CheckBox chkAsisPerson;
    private kevin.component.checkbox.CheckBox chkAsisTransp;
    private kevin.component.checkbox.CheckBox chkAviso;
    private kevin.component.checkbox.CheckBox chkCntProceso;
    private kevin.component.checkbox.CheckBox chkCntrEstados;
    private kevin.component.checkbox.CheckBox chkCosecha;
    private kevin.component.checkbox.CheckBox chkCostos;
    private kevin.component.checkbox.CheckBox chkCtrlBoletas;
    private kevin.component.checkbox.CheckBox chkDespacho;
    private kevin.component.checkbox.CheckBox chkFSanidad;
    private kevin.component.checkbox.CheckBox chkMaquinaria;
    private kevin.component.checkbox.CheckBox chkPacking;
    private kevin.component.checkbox.CheckBox chkParaDocID;
    private kevin.component.checkbox.CheckBox chkPreTareo;
    private kevin.component.checkbox.CheckBox chkProgramacion;
    private kevin.component.checkbox.CheckBox chkRecepcion;
    private kevin.component.checkbox.CheckBox chkRiego;
    private kevin.component.checkbox.CheckBox chkTareo;
    private kevin.component.combobox.ComboBox comboBox1;
    private javax.swing.JPanel contenedor;
    private kevin.component.edittext.EditText edtDescripcionn;
    private kevin.component.edittext.EditText edtIdMotivo;
    private kevin.component.edittext.EditText edtNombreCorto;
    private kevin.component.textarea.TextArea edtObservaciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelData;
    private javax.swing.JPanel panelImportar;
    private javax.swing.JPanel panelNuevoEditar;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.tabla.TablaSmart tablaImport;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void Eliminar(Object obj) {

    }

    @Override
    public void Eliminar(Object[] data) {

    }

    @Override
    public void Editar(Object obj) {

    }

    @Override
    public void Editar(Object[] data) {

    }

    @Override
    public void Cancelar() {

    }

}
