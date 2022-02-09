package gmo.internal;

import app.*;
import gmo.utils.jkeys;
import gmo.utils.jvalues;
import java.awt.CardLayout;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import jmugi.model.ImpAdminList;
import jmugi.voids.JMethods;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;

/**

 @author Asus
 */
public class AdminResponsablesCampo extends javax.swing.JInternalFrame implements ImpAdminList {

    CardLayout cardLayout;
    Window Frame;
    SmartLoader load;

    int EDITNUEVO = 0;

    public AdminResponsablesCampo(Window w) {
        this.Frame = w;
        initComponents();
        cardLayout = (CardLayout) contenedor.getLayout();

        goData();
    }

    public void actions(String action) {
        switch (action) {
            case "Cancelar":
            case "Regresar":
            case "Guardar":
                toolbar.setAGREGAR(true);
                toolbar.setANULAR(true);
                toolbar.setEDITAR(true);
                toolbar.setEXCEL(true);
                toolbar.setELIMINAR(true);
                toolbar.setIMPORTAR(false);
                toolbar.setGUARDAR(false);
                toolbar.setRECARGAR(true);
                toolbar.setREGRESAR(false);
                break;
            case "Agregar":
            case "Editar":
                toolbar.setAGREGAR(false);
                toolbar.setANULAR(false);
                toolbar.setEDITAR(false);
                toolbar.setEXCEL(false);
                toolbar.setELIMINAR(false);
                toolbar.setIMPORTAR(true);
                toolbar.setGUARDAR(true);
                toolbar.setRECARGAR(false);
                toolbar.setREGRESAR(true);
                break;
        }
    }

    private void initToolbar() {
        toolbar.setAGREGAR_CALLBACK(this::Agregar);
        toolbar.setEDITAR_CALLBACK(this::Editar);
        toolbar.setGUARDAR_CALLBACK(this::Guardar);
        toolbar.setELIMINAR_CALLBACK(this::Eliminar);
        toolbar.setANULAR_CALLBACK(this::Anular);
        toolbar.setRECARGAR_CALLBACK(this::GetData);
        toolbar.setREGRESAR_CALLBACK(this::Cancelar);

        toolbar.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, "Exportacion datos a Excel...");
        });
    }

    private void gettin_data() {
        goData();
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos de Vehiculos",
                "Se estan Descargando los Vehiculos registrados en la empresa",
                (Window frame) -> {
                    tabla.initHttp(
                            "0,1",
                            "iddatabase,idempresa,idvehiculo,idestado,ruc,razonsocial,dni,nombres,placa,tipomovilidad,capacidad,modelo,fechacontrato,procedencia",
                            "iddatabase,idempresa,idvehiculo,idestado,ruc,razonsocial,dni,nombres,placa,tipomovilidad,capacidad,modelo,fechacontrato,procedencia",
                            "Stringx3,Integerx1,Stringx6,Integerx1,Stringx3",
                            RunMain.gettin_pages.GetQuery() + "exec GetRptVehiculos '" + jkeys.IDDATABASE + "','" + jkeys.IDEMPRESA + "',1;"
                    );
                    tabla.GetDatosHTTP();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
        load = null;
        jvalues.DATA_VEHICULOS = tabla.getDATA();
//        tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new AdminVehiculos.FormatVehiculos());
        JMethods.updateInternalJTable(this, tabla);
    }

    @Override
    public void GetData() {
    }

    @Override
    public void Eliminar() {
    }

    @Override
    public void Agregar() {
    }

    @Override
    public void Editar() {
    }

    @Override
    public void Editar(Object obj) {
    }

    @Override
    public void Actualizar() {
    }

    @Override
    public void Anular() {
    }

    @Override
    public void Guardar() {
    }

    @Override
    public void Cancelar() {
    }

    @Override
    public void goFilter() {
        cardLayout.show(contenedor, "cardFilter");
    }

    @Override
    public void goData() {
        cardLayout.show(contenedor, "cardGeneral");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JPanel();
        panelData = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        panelNuevoEditar = new javax.swing.JPanel();
        toolbar = new kevin.component.toolbar.Toolbar();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Administracion de Responsables de Campo");

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setLayout(new java.awt.CardLayout());

        panelData.setOpaque(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
        panelData.setLayout(panelDataLayout);
        panelDataLayout.setHorizontalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        panelDataLayout.setVerticalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        contenedor.add(panelData, "cardGeneral");

        panelNuevoEditar.setOpaque(false);

        javax.swing.GroupLayout panelNuevoEditarLayout = new javax.swing.GroupLayout(panelNuevoEditar);
        panelNuevoEditar.setLayout(panelNuevoEditarLayout);
        panelNuevoEditarLayout.setHorizontalGroup(
            panelNuevoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );
        panelNuevoEditarLayout.setVerticalGroup(
            panelNuevoEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
        );

        contenedor.add(panelNuevoEditar, "cardFilter");

        getContentPane().add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar.setBackground(new java.awt.Color(255, 255, 255));
        toolbar.setAGREGAR(true);
        toolbar.setANULAR(true);
        toolbar.setEDITAR(true);
        toolbar.setELIMINAR(true);
        toolbar.setEXCEL(true);
        toolbar.setFILTRAR(false);
        toolbar.setIMPORTAR(false);
        toolbar.setIMPRIMIR(false);
        toolbar.setINDICADOR(false);
        toolbar.setIREPORT(false);
        toolbar.setRECARGAR(true);
        toolbar.setOpaque(true);
        getContentPane().add(toolbar, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelData;
    private javax.swing.JPanel panelNuevoEditar;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar;
    // End of variables declaration//GEN-END:variables

}
