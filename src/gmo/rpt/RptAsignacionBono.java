package gmo.rpt;

import app.RunMain;
import gmo.dialog.BuscarDialog;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import jmugi.voids.DateTimeUtil;
import jmugi.voids.JMethods;
import jmugi.voids.JOptionPane_methods;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**

 @author Asus
 */
public class RptAsignacionBono extends javax.swing.JInternalFrame {

    int TIPO = 0, CONCONSUMIDOR = 0;
    public Object[][] DATA;
    Window Frame;
    public CardLayout CARDLAYOUT;
    String FECHA_DATE1 = "", FECHA_DATE2 = "", IDUSER = "", TYPEREPORT = "ALC", IDACTIVIDAD = "", IDLABOR = "", IDCONSUMIDOR = "", OBSERVACIONES = "";

    String titles[];
    int tams[];

    BuscarDialog buscarLaborActividad;
    ArrayList<Object[]> DNIS;

    public RptAsignacionBono(Window w) {
        this.Frame = w;
        initComponents();

        chooserFecha1.setDate(new Date());
        FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha1.getDate());
        chooserFecha1.setCallback(() -> {
            FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha1.getDate());
        });
        tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        chooserFecha2.setDate(new Date());
        FECHA_DATE2 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha2.getDate());
        chooserFecha2.setCallback(() -> {
            FECHA_DATE2 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha2.getDate());
        });

        toolbar1.setRECARGAR_CALLBACK(this::gettin_data);
        toolbar1.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, "Asignacion de Bonos");
        });
        toolbar1.setFILTRAR_CALLBACK(() -> {
            CARDLAYOUT.show(contenedor, "cardFilter");
        });

        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, "cardFilter");

    }

    public void gettin_data() {
        tabla.initHttp("",
                "dni,nombres,cargo,asistencia,bono",
                "dni,nombres,cargo,asistencia,bono",
                "Stringx3,Doublex2",
                RunMain.gettin_pages.api_get()
                + "EXEC GetRptAsignacionBono "
                + "'" + jkeys.IDDATABASE + "', "
                + "'" + jkeys.IDEMPRESA + "','"
                + JMethods.getDETALLE_Object_XML("bonos", "item", DNIS) + "','"
                + FECHA_DATE1 + "', '" + FECHA_DATE2 + "';  "
        );
        tabla.GetDatosHTTP2022();
        DATA = tabla.getDATA();

        CARDLAYOUT.show(contenedor, "cardData");
        JMethods.updateInternalJTable(this, tabla);

    }

    private void inputFile() {

        DNIS = new ArrayList<>();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(null);
        int v = 0;
        if (fileChooser.getSelectedFile() != null) {
            File file = fileChooser.getSelectedFile();
            try {

                FileInputStream excelFile = new FileInputStream(file);
                Workbook workbook = new XSSFWorkbook(excelFile);
                Sheet datatypeSheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = datatypeSheet.iterator();
                iterator.next();
                Object[] val;
                while (iterator.hasNext()) {

                    Row currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    val = new Object[4];
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
                            val[v] = currentCell.getStringCellValue();
                        } else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            val[v] = currentCell.getNumericCellValue();
                        }
                        v++;
                    }
                    v = 0;
                    DNIS.add(val);
//                    PrintMethods.printer(Arrays.toString(val));
                    etiqueta3.setText(DNIS.size() + " Trabajadores Cargados...");
                    if (DNIS.size() > 0)
                        btnAplicar.setEnabled(true);

                }
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        smpExportar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        toolbar1 = new kevin.component.toolbar.Toolbar();
        contenedor = new javax.swing.JPanel();
        cardData = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        cardFilter = new javax.swing.JPanel();
        panel1 = new kevin.component.panel.Panel();
        jPanel2 = new javax.swing.JPanel();
        etiqueta2 = new kevin.component.label.Etiqueta();
        chooserFecha2 = new kevin.component.date.MaterialDateChooser();
        etiqueta1 = new kevin.component.label.Etiqueta();
        chooserFecha1 = new kevin.component.date.MaterialDateChooser();
        btnImportar = new kevin.component.button.ButtonImage();
        etiqueta3 = new kevin.component.label.Etiqueta();
        btnAplicar = new kevin.component.button.ButtonMaterialIcon();

        smpExportar.setText("jMenuItem1");
        smpExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smpExportarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(smpExportar);

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table);

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte de Asignacion de Bonos por Cargo");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        toolbar1.setEXCEL(true);
        toolbar1.setFILTRAR(true);
        toolbar1.setRECARGAR(true);
        jPanel4.add(toolbar1, java.awt.BorderLayout.WEST);

        contenedor.setLayout(new java.awt.CardLayout());

        cardData.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout cardDataLayout = new javax.swing.GroupLayout(cardData);
        cardData.setLayout(cardDataLayout);
        cardDataLayout.setHorizontalGroup(
            cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDataLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                .addContainerGap())
        );
        cardDataLayout.setVerticalGroup(
            cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(cardData, "cardData");

        cardFilter.setBackground(new java.awt.Color(255, 255, 255));
        cardFilter.setLayout(new java.awt.GridBagLayout());

        panel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setOpaque(false);

        etiqueta2.setText("Fin");

        chooserFecha2.setWeekOfYearVisible(false);

        etiqueta1.setText("Inicio");

        chooserFecha1.setWeekOfYearVisible(false);

        btnImportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/importExcel.png"))); // NOI18N
        btnImportar.setText("buttonImage1");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        etiqueta3.setText("Importar Trabajadores");

        btnAplicar.setText("buttonMaterialIcon1");
        btnAplicar.setICO(iconfont.MATERIALICON.MATERIALICONIC.DONE);
        btnAplicar.setICO_size(48.0F);
        btnAplicar.setEnabled(false);
        btnAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooserFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiqueta2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooserFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiqueta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAplicar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiqueta1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooserFecha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chooserFecha2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiqueta2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnImportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiqueta3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        panel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(42, 10, 578, 10);
        cardFilter.add(panel1, gridBagConstraints);

        contenedor.add(cardFilter, "cardFilter");

        jPanel4.add(contenedor, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void smpExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smpExportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_smpExportarActionPerformed

    private void btnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarActionPerformed
        JOptionPane_methods.MostrarConfirmacion(Frame, (Window frame) -> {
            gettin_data();
        });
    }//GEN-LAST:event_btnAplicarActionPerformed

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        inputFile();
    }//GEN-LAST:event_btnImportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.ButtonMaterialIcon btnAplicar;
    private kevin.component.button.ButtonImage btnImportar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel cardData;
    private javax.swing.JPanel cardFilter;
    private kevin.component.date.MaterialDateChooser chooserFecha1;
    private kevin.component.date.MaterialDateChooser chooserFecha2;
    private javax.swing.JPanel contenedor;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private kevin.component.panel.Panel panel1;
    private javax.swing.JMenuItem smpExportar;
    private kevin.component.tabla.TablaSmart tabla;
    private javax.swing.JTable table;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables
}
