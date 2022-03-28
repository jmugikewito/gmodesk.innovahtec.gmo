package gmo.panel;

import acore.principalvalues;
import app.RunMain;
import static gmo.core.MainLite.*;
import color.MaterialColor;
import gmo.dialog.BuscarDialog;
import gmo.utils.jkeys;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import jmugi.voids.JMethods;
import jmugi.voids.JOptionPane_methods;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;
import utils.ExecHTTP;

/**

 @author Asus
 */
public class EditTrabajadoresUnknow extends javax.swing.JPanel {

    private final String[] TITLES_TRABAJADORES = {"idtareo", "dni", "nombres", "itemid", "item", "hora_inicio", "hora_fin", "idmotivo", "motivo", "esjor", "esrend", "JOR", "REND", "AVA", "JOREX", "RENDEX", "tipoconcepto", "conceptobono", "bono", "observaciones", "editado"};
    private final int[] TAM_TRABAJADORES = {0, 90, 320, 70, 70, 100, 100, 100, 200, 70, 70, 80, 80, 80, 80, 80, 100, 200, 100, 240, 80};
    private String IDTAREO = "", IDPLANILLA = "";

    BuscarDialog buscarMotivos;
    BuscarDialog buscarTrabajadores;
    public Window Frame;
    boolean isNisira = false;
    public Object[][] DATA;
    private int ROW2 = 0;
    public SmartLoader load;
    public ArrayList<Object[]> LISTACHANGES;
    public ArrayList<Object[]> LISTADELETES;
    boolean hizeCambios1 = false;

    boolean hizeCambios2 = false;

    /**
     Frame - IDTAREO - ISNISIRA - JINTERN

     @param w
     @param VALUES
     */
    public EditTrabajadoresUnknow(Frame w, Object... VALUES) {
        initComponents();
        this.Frame = w;
        this.IDTAREO = VALUES[0].toString();
        this.isNisira = Boolean.parseBoolean(VALUES[1].toString());
        this.IDPLANILLA = VALUES[2].toString();
        this.tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.tabla.addKeyListener((KeyListener) new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evt) {
                try {
                    if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {

                    }
                } catch (Exception var2_2) {
                    // empty catch block
                }
            }
        });
        gettin_data();
        initBuscadores();
    }

    private void gettin_data() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando ...",
                "Se estan Descargando los Datos de Trabajadores Desconocidos",
                (Window frame) -> {
                    tabla.initHttp("0, 1",
                            "iddatabase,idempresa,idvehiculo,idestado,ruc,razonsocial,dni,nombres,placa,tipomovilidad,capacidad,modelo,fechacontrato,procedencia",
                            "iddatabase,idempresa,idvehiculo,idestado,ruc,razonsocial,dni,nombres,placa,tipomovilidad,capacidad,modelo,fechacontrato,procedencia",
                            "Stringx3,Integerx1,Stringx6,Integerx1,Stringx3",
                            gettin_pages.api_get() + "exec GetLisTareoTrabajadoresUnknow '" + IDTAREO + "';"
                    );
                    tabla.GetDatosHTTP2022();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
        load = null;
        this.tabla.setDefaultRenderer(Object.class, new MyTableCellRenderx(tabla.getColumnCount() - 1));

        hizeCambios1 = false;

        LISTACHANGES = new ArrayList<>();
        LISTADELETES = new ArrayList<>();

    }

    private void initBuscadores() {

        buscarMotivos = new BuscarDialog(
                (Frame) Frame,
                true,
                RunMain.CONECT.con,
                "idmotivo,descripcion",
                "120,360",
                "select idmotivo,descripcion from motivo where activo=1;",
                true,
                true);
        buscarMotivos.setTitle("Busqueda de Motivos");

        buscarTrabajadores = new BuscarDialog(
                (Frame) Frame,
                true,
                RunMain.CONECT.con,
                "iddatabase,idempresa,idtrabajador,nombresall,cnrodocumento,listanegra,liquidado,fecha_ingreso,fecha_cese,fecha_liquidado",
                "0, 0, 80, 200, 120, 100, 100, 100, 100, 100",
                ExecHTTP.parseQuery("select\n"
                        + "iddatabase,idempresa,idtrabajador,nombresall,cnrodocumento,listanegra,liquidado,fecha_ingreso,fecha_cese,fecha_liquidado\n"
                        + "from trabajador "
                        + "where iddatabase=?1 and idempresa=?2;",
                        true,
                        jkeys.IDDATABASE, jkeys.IDEMPRESA),
                true,
                true
        );
    }

    public void applyChanges(String detalleChanges, String procedure) {
        if (!LISTACHANGES.isEmpty()) {

            ExecHTTP.ExecPostProcedure(Frame,
                    gettin_pages.api_set(),
                    new String[]{"iddatabase2", "query"},
                    new Object[]{
                        jkeys.IDDATABASE2,
                        ExecHTTP.parseQL("exec " + procedure,
                                detalleChanges, (isNisira ? 1 : 0)
                        )
                    },
                    () -> {//ACTION DONE
                        setEditDelete();
                        gettin_data();
                    },
                    () -> {//ACTION WARN
                    });
        }
    }

    private void updateDistribucion() {
        if (tabla.getSelectedColumn() != 1) {
            if (JOptionPane.showConfirmDialog(this, "Esta seguro de actualizar los items seleccionados?", "Actualizando", 0) == 0) {
                int[] sel = this.tabla.getSelectedRows();
                switch (tabla.getSelectedColumn()) {
                    case 5:
                    case 6:
                    case 18:
                        String x = JOptionPane.showInputDialog(Frame, "Ingrese el Nuevo Texto");
                        if (x != null) {
                            for (int i = 0; i < sel.length; i++) {
                                tabla.setValueAt(x, sel[i], tabla.getSelectedColumn());
                                tabla.setValueAt("1", sel[i], tabla.getColumnCount() - 1);
                            }
                            setEditDelete();
                        }

                        break;
                    case 7:
                        JDialog.setDefaultLookAndFeelDecorated(false);
                        JMethods.settingGlassPane((JFrame) Frame, buscarMotivos, principalvalues.colorAccent, 0.6f);
                        if (buscarMotivos.DATA_SELECT != null) {
                            for (int i = 0; i < sel.length; i++) {
                                tabla.setValueAt(buscarMotivos.DATA_SELECT[0].toString(), sel[i], 7);
                                tabla.setValueAt(buscarMotivos.DATA_SELECT[1].toString(), sel[i], 8);
                                tabla.setValueAt("1", sel[i], tabla.getColumnCount() - 1);
                            }
                            buscarMotivos.DATA_SELECT = null;
                            setEditDelete();
                        }
                        JDialog.setDefaultLookAndFeelDecorated(true);
                        break;
                    case 9:
                    case 10:
                        int a = JOptionPane_methods.Input_Integer(Frame, (String) "Ingrese el Nuevo Valor");
                        if (a > 0) {
                            System.out.println("VALOR SELECCIONADO: a: " + a);
                            for (int i = 0; i < sel.length; i++) {
                                tabla.setValueAt(a, sel[i], tabla.getSelectedColumn());
                                tabla.setValueAt("1", sel[i], tabla.getColumnCount() - 1);
                            }
                            setEditDelete();
                        }
                        break;
                    case 11://JORNAL
                    case 12://RENDIM
                    case 13://AVANCE
                    case 14://JORNEX
                    case 15://JORNEX
                    case 17://BONO
                        double b = JOptionPane_methods.Input_Double(Frame, (String) "Ingrese el Nuevo Valor");
                        if (b > 0) {
                            for (int i = 0; i < sel.length; i++) {
                                tabla.setValueAt(b, sel[i], tabla.getSelectedColumn());
                                tabla.setValueAt("1", sel[i], tabla.getColumnCount() - 1);
                            }
                            setEditDelete();
                        }
                        break;
                }
            }
        }
    }

    public void setEditDelete() {
        hizeCambios2 = true;
        tabla.setDefaultRenderer(Object.class, new MyTableCellRenderx(tabla.getColumnCount() - 1));
        btnDel.setVisible(!hizeCambios2);
        btnClose.setVisible(hizeCambios2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitle = new javax.swing.JPanel();
        btnGuardar = new kevin.component.button.ButtonMaterialIcon();
        btnEditar = new kevin.component.button.ButtonMaterialIcon();
        btnClose = new kevin.component.button.ButtonMaterialIcon();
        btnDel = new kevin.component.button.ButtonMaterialIcon();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();

        setBackground(new java.awt.Color(255, 255, 255));

        panelTitle.setBackground(defaults.colorPrimaryDark);
        panelTitle.setOpaque(false);

        btnGuardar.setText("buttonMaterialIcon1");
        btnGuardar.setICO(iconfont.MATERIALICON.MATERIALICONIC.SAVE);
        btnGuardar.setICO_color(defaults.colorPrimaryDark);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar.setText("buttonMaterialIcon1");
        btnEditar.setICO(iconfont.MATERIALICON.MATERIALICONIC.EDIT);
        btnEditar.setICO_color(defaults.colorPrimaryDark);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnClose.setText("buttonMaterialIcon1");
        btnClose.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        btnClose.setICO_color(defaults.colorPrimaryDark);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnDel.setText("buttonMaterialIcon1");
        btnDel.setICO(iconfont.MATERIALICON.MATERIALICONIC.DELETE);
        btnDel.setICO_color(defaults.colorPrimaryDark);
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 698, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (hizeCambios2) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                if (tabla.getValueAt(i, tabla.getColumnCount() - 1).toString().equals("1")) {
                    LISTACHANGES.add(new Object[]{
                        tabla.getValueAt(i, 0),// idtareo
                        tabla.getValueAt(i, 3),// itemid
                        tabla.getValueAt(i, 4),// item
                        tabla.getValueAt(i, 1),// idtrabajador
                        tabla.getValueAt(i, 9),// esjornal
                        tabla.getValueAt(i, 10),// esrendimiento
                        tabla.getValueAt(i, 5),// inicio
                        tabla.getValueAt(i, 6),// fin
                        tabla.getValueAt(i, 11),// jornal
                        tabla.getValueAt(i, 12),// rendimiento
                        tabla.getValueAt(i, 13),// avance
                        tabla.getValueAt(i, 14),// jornalextra
                        tabla.getValueAt(i, 15),// rendimientoextra
                        tabla.getValueAt(i, 16),// conceptobono
                        tabla.getValueAt(i, 18),// bono
                        tabla.getValueAt(i, 7)// idmotivo
                    });
                }
            }
            String DETALLEENVIAR = JMethods.getDETALLE_Object_XML("ddtareo", "item", LISTACHANGES);
            System.out.println(DETALLEENVIAR);
            applyChanges(DETALLEENVIAR, "UpTrabajadores");
        } else {
            JOptionPane.showMessageDialog(this, "No encontramos Modificaciones");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        updateDistribucion();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        btnEditar.setVisible(true);
        btnGuardar.setVisible(true);
        btnClose.setVisible(false);
        LISTACHANGES.clear();
        gettin_data();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        ROW2 = tabla.getSelectedRow();
        if (evt.getClickCount() == 2) {
            if (tabla.getSelectedColumn() == 1) {
                JDialog.setDefaultLookAndFeelDecorated(false);
                JMethods.settingGlassPane((JFrame) Frame, buscarTrabajadores, principalvalues.colorAccent, 0.6f);
                if (buscarTrabajadores.DATA_SELECT != null) {
                    tabla.setValueAt(buscarTrabajadores.DATA_SELECT[2].toString(), ROW2, 1);
                    tabla.setValueAt(buscarTrabajadores.DATA_SELECT[3].toString(), ROW2, 2);
                    buscarTrabajadores.DATA_SELECT = null;
                    tabla.setValueAt("1", ROW2, tabla.getColumnCount() - 1);
                    setEditDelete();
                }
                JDialog.setDefaultLookAndFeelDecorated(true);
            }
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_E) {
            updateDistribucion();
        }
    }//GEN-LAST:event_tablaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.ButtonMaterialIcon btnClose;
    private kevin.component.button.ButtonMaterialIcon btnDel;
    private kevin.component.button.ButtonMaterialIcon btnEditar;
    private kevin.component.button.ButtonMaterialIcon btnGuardar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelTitle;
    private kevin.component.tabla.TablaSmart tabla;
    // End of variables declaration//GEN-END:variables

    class MyTableCellRenderx extends DefaultTableCellRenderer {

        private int patron;

        public MyTableCellRenderx(int pat) {
            this.patron = pat;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            switch (table.getValueAt(row, this.patron).toString()) {
                case "1":
                    this.setBackground(MaterialColor.AMBERA_200);
                    this.setForeground(Color.black);
                    break;
                case "0":
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.black);
                    break;
            }

            switch (column) {
                case 2:
                    if (table.getValueAt(row, column).toString().toLowerCase().contains("unknow")
                            || table.getValueAt(row, column).toString().toLowerCase().contains("desconocido")
                            || table.getValueAt(row, column).toString().toLowerCase().contains("no registrado")) {
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/person_stop.png")));
                        setBackground(MaterialColor.RED_200);
                        setForeground(MaterialColor.BLACK);
                    }
                    break;
                default:
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    setIcon(null);
            }

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return this;
        }
    }

}
