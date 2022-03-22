package gmo.internal;

import acore.principalvalues;
import app.RunMain;
import static gmo.core.MainLite.*;
import color.MaterialColor;
import gmo.dialog.BuscarDialog;
import gmo.dialog.NuevoSubTareoOnLine;
import gmo.methods.jmethods;
import gmo.utils.jkeys;
import gmo.utils.jvalues;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jmugi.component.TableRowUtilities;
import jmugi.model.Parameter;
import jmugi.voids.DateTimeUtil;
import jmugi.voids.JMethods;
import jmugi.voids.JOptionPane_methods;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;
import kevin.component.models.JMTableModel;
import kevin.component.toast.Toast;
import gmo.panel.EditTrabajadoresUnknow;
import utils.ExecHTTP;
import utils.jsonmethods;
import views.JMDialog.PDialogInputArea;

/**

 @author Asus
 */
public class AdminSubTareos extends javax.swing.JInternalFrame {

    int positionContainer = 1;
    JDialog DIALOG;
    public Object[][] DATA;
    public Object[][] DATA2;
    public Object[][] DATAD;

    boolean ES_GENERAL = false;
    public ArrayList<Object[]> LISTACHANGES;
    public ArrayList<Object[]> LISTADELETES;
    public ArrayList<Object[]> LISTAADD;

    public Window Frame;
    public CardLayout CARDLAYOUT;
    public SmartLoader load;
    public SmartLoader xload;
    private String ITEM = "", ITEM_ULTIMO, IDTAREO = "", IDUSUARIO = "", IDPLANILLA = "", FECHA_DATE = "", IDESTADO = "";
    private int ROW1 = 0;
    private int ROW2 = 0;

    private JMTableModel tablemodelTRABAJADORES;
    private String[] TITLES_TRABAJADORES = new String[]{"idtareo", "dni", "nombres", "itemid", 
                            "item", "hora_inicio", "hora_fin", "inicio_pausa", "fin_pausa", "idmotivo", 
                            "motivo", "esjor", "esrend", "JOR", "REND", "AVA", "JORNO", "JOR_COMP", "RENDEX", 
                            "tipoconcepto", "conceptobono", "bono", "observaciones", "diasiguiente", "editado"};
   private int[] TAM_TRABAJADORES = new int[] {0, 85, 280, 0, 50, 80, 80, 80, 80, 80, 100, 60, 60, 70, 80, 80, 80, 110, 0, 0, 0, 0, 200, 100, 0};
   
   /*
   Columnas de ddtareo de la tabla2
    0	idtareo,
    1	dni,
    2	nombres,
    3	itemid,
    4	item,
    5	hora_inicio,
    6	hora_fin,
    7	iniciorefrigerio,
    8	finrefrigerio,
    9	idmotivo,
    10	motivo,
    11	esjor,
    12	esrend,
    13	JOR,
    14	REND,
    15	AVA,
    16	JORNO,
    17	JOR_COMP,
    18	RENDEX,
    19	tipoconcepto,
    20	conceptobono,
    21	bono,
    22	observaciones,
    23	diasiguiente,
    24	0 editado
*/
   
    BuscarDialog buscarCultivoVariedad;
    BuscarDialog buscarLaborActividad;
    BuscarDialog buscarConsumidores;
    BuscarDialog buscarMotivos;
    BuscarDialog buscarTrabajadores;

    boolean hizeCambios1 = false;
    boolean hizeCambios2 = false;
    boolean estaEliminando = false;
    boolean estaAgregando = false;
    boolean isNisira = false;
    JInternalFrame internal;
    boolean PUEDE_EDITAR = false;

    boolean CARGAMOSDATOS = false;

    public AdminSubTareos(Frame w, String idtareo, String idusuario, String idplanilla, boolean isnisira, String idestado) {
        this.Frame = w;
        this.IDTAREO = idtareo;
        this.IDUSUARIO = idusuario;
        this.IDPLANILLA = idplanilla;
        this.isNisira = isnisira;
        this.IDESTADO = idestado;
        System.out.println("EN NISIRA? : " + isNisira);
        initComponents();
        tabla2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablemodelTRABAJADORES = new JMTableModel();
        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, "card1");
        this.internal = this;
        positionContainer = 1;
        action();
        jTabbedPane1.addTab("Trabajadores Desconocidos en Planilla", jPanel2);
        jPanel2.add(new EditTrabajadoresUnknow(w, IDTAREO, isNisira, IDPLANILLA));
        panelCabecera.setVisible(false);
        cargarSubTareos();
        buscarCulVar_Motivos();

        PUEDE_EDITAR = jvalues.USUARIO.getTipousuario().getEsdigitador1() == 1 || jvalues.USUARIO.getTipousuario().getEsdigitador1() == 1;
        if (PUEDE_EDITAR == false) {
            PUEDE_EDITAR = jvalues.USUARIO.getTipousuario().getEdita() == 1;
        }

        if (jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("JEFERRHH")) {
            if (IDESTADO.equals("CO")) {
                PUEDE_EDITAR = true;
            } else {
                PUEDE_EDITAR = false;
            }
        } else {
            PUEDE_EDITAR = false;
        }

        switch (jkeys.IDCLIENTE) {
            case "20103272964":
                chkConfigSubTareos.setSelected(true);
                break;
            case "20554556192":
                chkConfigSubTareos.setSelected(false);
                break;
            default:
                chkConfigSubTareos.setSelected(false);
        }

        jmethods.cargarPlanillas(cboPlanilla, " 'u','9' ");
        GetParameterEstados();
        
        sm_corregirdni.setVisible(false); //MVS cree que esta de más
    }

    public AdminSubTareos(String title, Frame w, boolean isNisira) {
        this.Frame = w;
        this.IDTAREO = "";
        this.IDUSUARIO = "";
        this.IDPLANILLA = "";
        this.isNisira = isNisira;
        this.ES_GENERAL = true;
        initComponents();
        this.internal = this;
        chooserFecha.setDate(new Date());
        FECHA_DATE = DateTimeUtil.getDate_yyyyMMdd(chooserFecha.getDate());

        chooserFecha.setCallback(() -> {
            FECHA_DATE = DateTimeUtil.getDate_yyyyMMdd(chooserFecha.getDate());
            System.out.println("FECHA SELECTED: " + FECHA_DATE);
            initUsuariosPlanillas();
        });
        tabla2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, "card1");
        tablemodelTRABAJADORES = new JMTableModel();
        positionContainer = 1;
        action();
        buscarCulVar_Motivos();
        PUEDE_EDITAR = jvalues.USUARIO.getTipousuario().getEsdigitador1() == 1 || jvalues.USUARIO.getTipousuario().getEsdigitador1() == 1;
        if (PUEDE_EDITAR == false) {
            PUEDE_EDITAR = jvalues.USUARIO.getTipousuario().getEdita() == 1;
        }

        switch (jkeys.IDCLIENTE) {
            case "20103272964":
                chkConfigSubTareos.setSelected(true);
                break;
            case "20554556192":
                chkConfigSubTareos.setSelected(false);
                break;
            default:
                chkConfigSubTareos.setSelected(false);
        }

        jmethods.cargarPlanillas(cboPlanilla, " 'u','9' ");
        GetParameterEstados();
        sm_corregirdni.setVisible(false); //MVS cree que esta de más
    }

    private void GetParameterEstados() {
        Parameter p;
        for (int i = 0; i < jvalues.LIST_PARAMETROS.size(); i++) {
            if (jvalues.LIST_PARAMETROS.get(i).getIdparametro().equals("ESTADOS_SUBTAREO")) {
                p = jvalues.LIST_PARAMETROS.get(i);
                System.out.println("ESTADO DTAREO: "+p.getValor1());
                cboEstado.setModel(new javax.swing.DefaultComboBoxModel(p.getValor1().split(",")));
            }
        }
    }

    private void initUsuariosPlanillas() {

        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos",
                "Se estan Cargando los Trabajadores y las Planillas de la Fecha Seleccionada", (Window frame) -> {
                    ArrayList<Object[]> U1 = jsonmethods.GetData2(null,
                            gettin_pages.api_get() + ExecHTTP.parseQL("exec GetListUsuarioxRegister ", jkeys.IDDATABASE, jkeys.IDEMPRESA, "TAR", chooserFecha.toStringDate(), IDPLANILLA, cboEstado.getSelectedItem().toString()),
                            "idusuario",
                            "Stringx1"
                    );

                    cboUser.removeAll();
                    cboUser.removeAllItems();
                    if (U1.size() > 0) {
                        cboUser.removeAll();
                        cboUser.repaint();
                        cboUser.addItem("");
                        for (int i = 0; i < U1.size(); i++) {
                            cboUser.addItem(U1.get(i)[0]);
                        }
                    } else {
                        cboUser.removeAll();
                        cboUser.removeAllItems();
                        cboUser.repaint();
                        cboUser.addItem("");
                    }
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, MaterialColor.BLUEGREY_900, 0.8f);
        load = null;

    }

    private void cargarSubTareos() {
        tabla2.setComponentPopupMenu(null);
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos del SubTareo",
                "Se estan Descargando los SubTareos... un momento",
                (Window frame) -> {
                    if (ES_GENERAL) {
                        tablaSubTareos.initHttp("0, 17, 18",
                                "idtareo, item, idusuario, horas, personas, jornal_fisico, rendimiento, promedio, idcultivovariedad, cultivo, variedad, idactividad, actividad, idlabor, labor, idconsumidor, consumidor, detalle, editado",
                                "idtareo, item, idusuario, horas, personas, jornal_fisico, rendimiento, promedio, idcultivovariedad, cultivo, variedad, idactividad, actividad, idlabor, labor, idconsumidor, consumidor, detalle, editado",
                                "Stringx3,Doublex5,Stringx10,Integerx1",
                                gettin_pages.api_get() + "exec GetDetalleSubTareoSwift2022 "
                                + "'" + jkeys.IDDATABASE + "',"
                                + "'" + jkeys.IDEMPRESA + "',"
                                + "'" + (chooserFecha.getDate() == null ? "" : DateTimeUtil.getDate_yyyyMMdd(chooserFecha.getDate()).replace("-", "")) + "',"
                                + "'" + cboUser.getSelectedItem().toString() + "',"
                                + "'" + IDPLANILLA + "',"
                                + "0,"
                                + "'" + cboEstado.getSelectedItem().toString() + "',"
                                + (chkCargaTrab.isSelected() ? 0 : 1) + ";"
                        );
                        tablaSubTareos.GetDatosHTTP();
                        TITLES_TRABAJADORES = new String[]{"idtareo", "dni", "nombres", "itemid", 
                            "item", "hora_inicio", "hora_fin", "inicio_pausa", "fin_pausa", "idmotivo", 
                            "motivo", "esjor", "esrend", "JOR", "REND", "AVA", "JORNO", "JOR_COMP", "RENDEX", 
                            "tipoconcepto", "conceptobono", "bono", "observaciones", "diasiguiente", "editado"};
                        TAM_TRABAJADORES = new int[]{0, 85, 280, 0, 50, 80, 80, 80, 80, 80, 100, 60, 60, 70, 80, 80, 80, 110, 0, 0, 0, 0, 200, 100, 0};

                    }
                    tablaSubTareos.setDefaultRenderer(Object.class, new FormatSubTareo());
                    DATA = tablaSubTareos.getDATA();
                    JMethods.updateInternalJTable(this, tablaSubTareos);
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, MaterialColor.BLUEGREY_900, 0.8f);
        JMethods.updateInternalJTable(this, tablaSubTareos);
        load = null;
        hizeCambios1 = false;
        hizeCambios2 = false;

        LISTACHANGES = new ArrayList<>();
        LISTADELETES = new ArrayList<>();
        LISTAADD = new ArrayList<>();

        btnDistribucion.setVisible(hizeCambios1);
        btnDel.setVisible(!hizeCambios1);

        apply(1);
        CARGAMOSDATOS = true;
    }

    private void action() {
        switch (positionContainer) {
            case 1:
                btnDistribucion.setVisible(false);
                btnEditar.setVisible(PUEDE_EDITAR);
                btnDel.setVisible(PUEDE_EDITAR);
                btnAgregar.setVisible(false);
                btnGuardar.setVisible(false);
                btnClose.setVisible(false);
                btnDelRow.setVisible(false);
                break;
            case 2:
                btnDistribucion.setVisible(false);
                btnEditar.setVisible(PUEDE_EDITAR);
                btnDel.setVisible(PUEDE_EDITAR);
                btnAgregar.setVisible(PUEDE_EDITAR);
                btnGuardar.setVisible(false);
                btnClose.setVisible(true);
                btnDelRow.setVisible(false);
                break;
        }
    }

    private void apply(int num) {
        positionContainer = num;
        CARDLAYOUT.show(contenedor, "card" + num);
        action();
    }

    private void buscarCulVar_Motivos() {
        buscarCultivoVariedad = new BuscarDialog(
                Frame,
                true,
                RunMain.CONECT.con,
                "iddatabase,idempresa,idcultivo, cultivo, idvariedad, variedad, idcultivovariedad, cultivovariedad",
                "0, 0, 120, 200, 120, 200, 0, 0",
                ExecHTTP.parseQuery("select iddatabase,idempresa,idcultivo, cultivo, idvariedad, variedad, idcultivovariedad, cultivovariedad\n"
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

    }

    private void initActividadLabor(String idcultivo, String idvariedad) {
        buscarLaborActividad = new BuscarDialog(
                Frame,
                true,
                RunMain.CONECT.con,
                "idlabor,labor,idactividad,actividad,esrendimiento",
                "80, 280, 80, 280",
                chkConfigSubTareos.isSelected()
                ? (ExecHTTP.parseQuery("SELECT\n"
                        + "  DISTINCT\n"
                        + "  lab.idlabor,\n"
                        + "  ltrim(rtrim(lab.descripcion)) labor,\n"
                        + "  lab.idactividad,\n"
                        + "  ltrim(rtrim(ac.descripcion))  actividad,"
                        + "  ac.esrendimiento \n"
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
                        + "  ac.esrendimiento \n"
                        + "FROM labor LAB\n"
                        + "  INNER JOIN actividad ac ON ltrim(rtrim(ac.idactividad)) = ltrim(rtrim(lab.idactividad)) AND ac.idempresa = lab.idempresa\n"
                        + "WHERE lab.idempresa=?1 and lab.activo=?2;",
                        true,
                        jkeys.IDEMPRESA, 1
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
                : ExecHTTP.parseQuery("select  idconsumidor, descripcion "
                        + "from consumidor "
                        + "where TIPO in (?1) and idempresa=?2  "
                        + " and activo=?3 and length(fechabaja) = 0;",
                        true,
                        "T','F','R','M','O','X", jkeys.IDEMPRESA, 1),
                true,
                true
        );
        buscarConsumidores.setTitle("Busqueda de Consumidores");
    }

    private void setEditDelete() {
        switch (positionContainer) {
            case 1:
                hizeCambios1 = true;
                tablaSubTareos.setDefaultRenderer(Object.class, new MyTableCellRender(tablaSubTareos.getColumnCount() - 1));
                btnDistribucion.setVisible(!hizeCambios1);
                btnDel.setVisible(!hizeCambios1);
                btnClose.setVisible(hizeCambios1);
                btnGuardar.setVisible(hizeCambios1);
                break;
            case 2:
                hizeCambios2 = true;
                tabla2.setDefaultRenderer(Object.class, new MyTableCellRender(tabla2.getColumnCount() - 1));
                btnDistribucion.setVisible(!hizeCambios2);
                btnDel.setVisible(!hizeCambios2);
                btnClose.setVisible(hizeCambios2);
                btnGuardar.setVisible(hizeCambios2);
                break;
        }
    }

    private void export_excel() {
        tablaSubTareos.exportExcel((JFrame) Frame, tablaSubTareos.getDATA(), tablaSubTareos.getTITLES(), getTitle());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        sm_corregirdni = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panelEditarTareo = new javax.swing.JPanel();
        btnDistribucion = new kevin.component.button.ButtonMaterialIcon();
        btnEditar = new kevin.component.button.ButtonMaterialIcon();
        btnAgregar = new kevin.component.button.ButtonMaterialIcon();
        btnDel = new kevin.component.button.ButtonMaterialIcon();
        btnDelRow = new kevin.component.button.ButtonMaterialIcon();
        btnClose = new kevin.component.button.ButtonMaterialIcon();
        btnGuardar = new kevin.component.button.ButtonMaterialIcon();
        btnExportExcel = new kevin.component.button.ButtonMaterialIcon();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelALL = new javax.swing.JPanel();
        panelCabecera = new kevin.component.panel.Panel();
        panelGeneralTareo = new javax.swing.JPanel();
        etiqueta2 = new kevin.component.label.Etiqueta();
        buttonMaterialIcon2 = new kevin.component.button.ButtonMaterialIcon();
        cboPlanilla = new kevin.component.combobox.ComboBox();
        jLabel1 = new javax.swing.JLabel();
        etiqueta4 = new kevin.component.label.Etiqueta();
        cboEstado = new kevin.component.combobox.ComboBox();
        jLabel2 = new javax.swing.JLabel();
        etiqueta5 = new kevin.component.label.Etiqueta();
        buttonMaterialIcon3 = new kevin.component.button.ButtonMaterialIcon();
        chooserFecha = new kevin.component.date.MaterialDateChooser();
        etiqueta3 = new kevin.component.label.Etiqueta();
        buttonMaterialIcon1 = new kevin.component.button.ButtonMaterialIcon();
        cboUser = new kevin.component.combobox.ComboBox();
        jLabel4 = new javax.swing.JLabel();
        button1 = new kevin.component.button.Button();
        jLabel3 = new javax.swing.JLabel();
        chkCargaTrab = new kevin.component.checkbox.CheckBox();
        contenedor = new javax.swing.JPanel();
        panelSubTareos = new kevin.component.panel.Panel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaSubTareos = new kevin.component.tabla.TablaSmart();
        panelDistribucion = new kevin.component.panel.Panel();
        scrollDistirbucion = new javax.swing.JScrollPane();
        tabla2 = new kevin.component.tabla.Tabla();
        etiqueta1 = new kevin.component.label.Etiqueta();
        paneDashboard = new javax.swing.JPanel();
        edtPromRendim = new kevin.component.edittext.EditText();
        edtPromJornal = new kevin.component.edittext.EditText();
        edtMinimo = new kevin.component.edittext.EditText();
        edtMaximo = new kevin.component.edittext.EditText();
        edtTotalPlantas = new kevin.component.edittext.EditText();
        edtTotalTrab = new kevin.component.edittext.EditText();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        panelEditarTareo1 = new javax.swing.JPanel();
        btnDistribucion1 = new kevin.component.button.ButtonMaterialIcon();
        btnEditar1 = new kevin.component.button.ButtonMaterialIcon();
        btnAgregar1 = new kevin.component.button.ButtonMaterialIcon();
        btnDel1 = new kevin.component.button.ButtonMaterialIcon();
        btnDelRow1 = new kevin.component.button.ButtonMaterialIcon();
        btnClose1 = new kevin.component.button.ButtonMaterialIcon();
        btnGuardar1 = new kevin.component.button.ButtonMaterialIcon();
        btnExportExcel1 = new kevin.component.button.ButtonMaterialIcon();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelALL1 = new javax.swing.JPanel();
        panelCabecera1 = new kevin.component.panel.Panel();
        panelGeneralTareo1 = new javax.swing.JPanel();
        etiqueta6 = new kevin.component.label.Etiqueta();
        buttonMaterialIcon4 = new kevin.component.button.ButtonMaterialIcon();
        cboPlanilla1 = new kevin.component.combobox.ComboBox();
        jLabel5 = new javax.swing.JLabel();
        etiqueta7 = new kevin.component.label.Etiqueta();
        cboEstado1 = new kevin.component.combobox.ComboBox();
        jLabel6 = new javax.swing.JLabel();
        etiqueta8 = new kevin.component.label.Etiqueta();
        buttonMaterialIcon5 = new kevin.component.button.ButtonMaterialIcon();
        chooserFecha1 = new kevin.component.date.MaterialDateChooser();
        etiqueta9 = new kevin.component.label.Etiqueta();
        buttonMaterialIcon6 = new kevin.component.button.ButtonMaterialIcon();
        cboUser1 = new kevin.component.combobox.ComboBox();
        jLabel7 = new javax.swing.JLabel();
        button2 = new kevin.component.button.Button();
        jLabel8 = new javax.swing.JLabel();
        chkCargaTrab1 = new kevin.component.checkbox.CheckBox();
        contenedor1 = new javax.swing.JPanel();
        panelSubTareos1 = new kevin.component.panel.Panel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaSubTareos1 = new kevin.component.tabla.TablaSmart();
        panelDistribucion1 = new kevin.component.panel.Panel();
        scrollDistirbucion1 = new javax.swing.JScrollPane();
        tabla3 = new kevin.component.tabla.Tabla();
        etiqueta10 = new kevin.component.label.Etiqueta();
        paneDashboard1 = new javax.swing.JPanel();
        edtPromRendim1 = new kevin.component.edittext.EditText();
        edtPromJornal1 = new kevin.component.edittext.EditText();
        edtMinimo1 = new kevin.component.edittext.EditText();
        edtMaximo1 = new kevin.component.edittext.EditText();
        edtTotalPlantas1 = new kevin.component.edittext.EditText();
        edtTotalTrab1 = new kevin.component.edittext.EditText();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        chkConfigSubTareos1 = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        chkConfigSubTareos = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        sm_corregirdni.setText("Corregir Dni");
        sm_corregirdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sm_corregirdniActionPerformed(evt);
            }
        });
        jPopupMenu1.add(sm_corregirdni);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Administracion de SubTareos");

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(720, 673));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(720, 645));
        jPanel1.setLayout(new java.awt.BorderLayout());

        panelEditarTareo.setOpaque(false);
        panelEditarTareo.setPreferredSize(new java.awt.Dimension(32, 48));

        btnDistribucion.setText("buttonMaterialIcon1");
        btnDistribucion.setICO(iconfont.MATERIALICON.MATERIALICONIC.LIST);
        btnDistribucion.setICO_color(defaults.colorPrimaryDark);
        btnDistribucion.setPreferredSize(new java.awt.Dimension(56, 56));
        btnDistribucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDistribucionActionPerformed(evt);
            }
        });
        panelEditarTareo.add(btnDistribucion);

        btnEditar.setText("buttonMaterialIcon1");
        btnEditar.setICO(iconfont.MATERIALICON.MATERIALICONIC.EDIT);
        btnEditar.setICO_color(defaults.colorPrimaryDark);
        btnEditar.setPreferredSize(new java.awt.Dimension(56, 56));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        panelEditarTareo.add(btnEditar);

        btnAgregar.setText("buttonMaterialIcon1");
        btnAgregar.setICO(iconfont.MATERIALICON.MATERIALICONIC.ADD);
        btnAgregar.setPreferredSize(new java.awt.Dimension(56, 56));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        panelEditarTareo.add(btnAgregar);

        btnDel.setText("buttonMaterialIcon1");
        btnDel.setICO(iconfont.MATERIALICON.MATERIALICONIC.DELETE);
        btnDel.setICO_color(defaults.colorPrimaryDark);
        btnDel.setPreferredSize(new java.awt.Dimension(56, 56));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });
        panelEditarTareo.add(btnDel);

        btnDelRow.setText("buttonMaterialIcon1");
        btnDelRow.setICO(iconfont.MATERIALICON.MATERIALICONIC.DELETE_FOREVER);
        btnDelRow.setPreferredSize(new java.awt.Dimension(56, 56));
        btnDelRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelRowActionPerformed(evt);
            }
        });
        panelEditarTareo.add(btnDelRow);

        btnClose.setText("buttonMaterialIcon1");
        btnClose.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        btnClose.setICO_color(defaults.colorPrimaryDark);
        btnClose.setPreferredSize(new java.awt.Dimension(56, 56));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        panelEditarTareo.add(btnClose);

        btnGuardar.setText("buttonMaterialIcon1");
        btnGuardar.setICO(iconfont.MATERIALICON.MATERIALICONIC.SAVE);
        btnGuardar.setICO_color(defaults.colorPrimaryDark);
        btnGuardar.setPreferredSize(new java.awt.Dimension(56, 56));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelEditarTareo.add(btnGuardar);

        btnExportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excelgreen24.png"))); // NOI18N
        btnExportExcel.setText("buttonMaterialIcon4");
        btnExportExcel.setPreferredSize(new java.awt.Dimension(56, 56));
        btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelActionPerformed(evt);
            }
        });
        panelEditarTareo.add(btnExportExcel);

        jPanel1.add(panelEditarTareo, java.awt.BorderLayout.WEST);

        jScrollPane1.setToolTipText("");

        panelALL.setOpaque(false);
        panelALL.setPreferredSize(new java.awt.Dimension(800, 645));
        panelALL.setLayout(new java.awt.BorderLayout());

        panelCabecera.setPreferredSize(new java.awt.Dimension(1116, 52));
        panelCabecera.setLayout(new java.awt.BorderLayout());

        panelGeneralTareo.setOpaque(false);
        panelGeneralTareo.setPreferredSize(new java.awt.Dimension(401, 42));

        etiqueta2.setText("Planilla");
        panelGeneralTareo.add(etiqueta2);

        buttonMaterialIcon2.setText("buttonMaterialIcon1");
        buttonMaterialIcon2.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon2.setICO_size(18.0F);
        buttonMaterialIcon2.setPreferredSize(new java.awt.Dimension(28, 27));
        buttonMaterialIcon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon2ActionPerformed(evt);
            }
        });
        panelGeneralTareo.add(buttonMaterialIcon2);

        cboPlanilla.setPreferredSize(new java.awt.Dimension(220, 32));
        cboPlanilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPlanillaActionPerformed(evt);
            }
        });
        panelGeneralTareo.add(cboPlanilla);

        jLabel1.setText("      ");
        panelGeneralTareo.add(jLabel1);

        etiqueta4.setText("Estado  ");
        panelGeneralTareo.add(etiqueta4);

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PE", "AP" }));
        cboEstado.setPreferredSize(new java.awt.Dimension(56, 34));
        cboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstadoActionPerformed(evt);
            }
        });
        panelGeneralTareo.add(cboEstado);

        jLabel2.setText("       ");
        panelGeneralTareo.add(jLabel2);

        etiqueta5.setText("Fecha");
        panelGeneralTareo.add(etiqueta5);

        buttonMaterialIcon3.setText("buttonMaterialIcon1");
        buttonMaterialIcon3.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon3.setICO_size(18.0F);
        buttonMaterialIcon3.setPreferredSize(new java.awt.Dimension(28, 27));
        buttonMaterialIcon3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon3ActionPerformed(evt);
            }
        });
        panelGeneralTareo.add(buttonMaterialIcon3);

        chooserFecha.setPreferredSize(new java.awt.Dimension(130, 36));
        chooserFecha.setWeekOfYearVisible(false);
        panelGeneralTareo.add(chooserFecha);

        etiqueta3.setText("      Usuario");
        panelGeneralTareo.add(etiqueta3);

        buttonMaterialIcon1.setText("buttonMaterialIcon1");
        buttonMaterialIcon1.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon1.setICO_size(18.0F);
        buttonMaterialIcon1.setPreferredSize(new java.awt.Dimension(28, 27));
        buttonMaterialIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon1ActionPerformed(evt);
            }
        });
        panelGeneralTareo.add(buttonMaterialIcon1);

        cboUser.setPreferredSize(new java.awt.Dimension(200, 32));
        cboUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUserActionPerformed(evt);
            }
        });
        panelGeneralTareo.add(cboUser);

        jLabel4.setText("      ");
        panelGeneralTareo.add(jLabel4);

        button1.setText("Cargar");
        button1.setPreferredSize(new java.awt.Dimension(120, 32));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panelGeneralTareo.add(button1);

        jLabel3.setText("             ");
        panelGeneralTareo.add(jLabel3);

        chkCargaTrab.setText("Omitir Trabajadores");
        chkCargaTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCargaTrabActionPerformed(evt);
            }
        });
        panelGeneralTareo.add(chkCargaTrab);

        panelCabecera.add(panelGeneralTareo, java.awt.BorderLayout.CENTER);

        panelALL.add(panelCabecera, java.awt.BorderLayout.NORTH);

        contenedor.setBackground(new java.awt.Color(255, 255, 255));
        contenedor.setLayout(new java.awt.CardLayout());

        tablaSubTareos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaSubTareos.setColumnSelectionAllowed(true);
        tablaSubTareos.setIsFilterable(false);
        tablaSubTareos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSubTareosMouseClicked(evt);
            }
        });
        tablaSubTareos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaSubTareosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaSubTareos);

        javax.swing.GroupLayout panelSubTareosLayout = new javax.swing.GroupLayout(panelSubTareos);
        panelSubTareos.setLayout(panelSubTareosLayout);
        panelSubTareosLayout.setHorizontalGroup(
            panelSubTareosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSubTareosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSubTareosLayout.setVerticalGroup(
            panelSubTareosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSubTareosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(panelSubTareos, "card1");

        panelDistribucion.setLayout(new java.awt.BorderLayout());

        scrollDistirbucion.setOpaque(false);

        tabla2.setCOLUMNA("");
        tabla2.setCOLUMNAS(19);
        tabla2.setTAMS(TAM_TRABAJADORES);
        tabla2.setColumnSelectionAllowed(true);
        tabla2.setFocusable(true);
        tabla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla2MouseClicked(evt);
            }
        });
        tabla2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla2KeyPressed(evt);
            }
        });
        scrollDistirbucion.setViewportView(tabla2);

        panelDistribucion.add(scrollDistirbucion, java.awt.BorderLayout.CENTER);

        etiqueta1.setESITALIC(true);
        etiqueta1.setFONT_SIZE(17.0F);
        etiqueta1.setPreferredSize(new java.awt.Dimension(59, 35));
        panelDistribucion.add(etiqueta1, java.awt.BorderLayout.PAGE_START);

        contenedor.add(panelDistribucion, "card2");

        panelALL.add(contenedor, java.awt.BorderLayout.CENTER);

        paneDashboard.setMinimumSize(new java.awt.Dimension(880, 48));
        paneDashboard.setOpaque(false);
        paneDashboard.setPreferredSize(new java.awt.Dimension(876, 48));
        paneDashboard.setLayout(new java.awt.GridLayout(1, 5, 20, 0));

        edtPromRendim.setEditable(false);
        edtPromRendim.setText("0.0");
        edtPromRendim.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtPromRendim.setLabel("Promedio Rendim");
        edtPromRendim.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard.add(edtPromRendim);

        edtPromJornal.setEditable(false);
        edtPromJornal.setText("0.0");
        edtPromJornal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtPromJornal.setLabel("Promedio Jornal");
        edtPromJornal.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard.add(edtPromJornal);

        edtMinimo.setEditable(false);
        edtMinimo.setText("0.0");
        edtMinimo.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtMinimo.setLabel("Minimo");
        edtMinimo.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard.add(edtMinimo);

        edtMaximo.setEditable(false);
        edtMaximo.setText("0.0");
        edtMaximo.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtMaximo.setLabel("Maximo");
        edtMaximo.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard.add(edtMaximo);

        edtTotalPlantas.setEditable(false);
        edtTotalPlantas.setText("0.0");
        edtTotalPlantas.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtTotalPlantas.setLabel("Total Plantas");
        edtTotalPlantas.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard.add(edtTotalPlantas);

        edtTotalTrab.setEditable(false);
        edtTotalTrab.setText("0.0");
        edtTotalTrab.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtTotalTrab.setLabel("Total Trabajadores");
        edtTotalTrab.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard.add(edtTotalTrab);

        panelALL.add(paneDashboard, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setViewportView(panelALL);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Edicion por SubTareos", jPanel1);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("Administracion de SubTareos");

        jTabbedPane2.setPreferredSize(new java.awt.Dimension(720, 673));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(720, 645));

        panelEditarTareo1.setOpaque(false);
        panelEditarTareo1.setPreferredSize(new java.awt.Dimension(32, 48));

        btnDistribucion1.setText("buttonMaterialIcon1");
        btnDistribucion1.setICO(iconfont.MATERIALICON.MATERIALICONIC.LIST);
        btnDistribucion1.setICO_color(defaults.colorPrimaryDark);
        btnDistribucion1.setPreferredSize(new java.awt.Dimension(56, 56));
        btnDistribucion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDistribucion1ActionPerformed(evt);
            }
        });
        panelEditarTareo1.add(btnDistribucion1);

        btnEditar1.setText("buttonMaterialIcon1");
        btnEditar1.setICO(iconfont.MATERIALICON.MATERIALICONIC.EDIT);
        btnEditar1.setICO_color(defaults.colorPrimaryDark);
        btnEditar1.setPreferredSize(new java.awt.Dimension(56, 56));
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });
        panelEditarTareo1.add(btnEditar1);

        btnAgregar1.setText("buttonMaterialIcon1");
        btnAgregar1.setICO(iconfont.MATERIALICON.MATERIALICONIC.ADD);
        btnAgregar1.setPreferredSize(new java.awt.Dimension(56, 56));
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        panelEditarTareo1.add(btnAgregar1);

        btnDel1.setText("buttonMaterialIcon1");
        btnDel1.setICO(iconfont.MATERIALICON.MATERIALICONIC.DELETE);
        btnDel1.setICO_color(defaults.colorPrimaryDark);
        btnDel1.setPreferredSize(new java.awt.Dimension(56, 56));
        btnDel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel1ActionPerformed(evt);
            }
        });
        panelEditarTareo1.add(btnDel1);

        btnDelRow1.setText("buttonMaterialIcon1");
        btnDelRow1.setICO(iconfont.MATERIALICON.MATERIALICONIC.DELETE_FOREVER);
        btnDelRow1.setPreferredSize(new java.awt.Dimension(56, 56));
        btnDelRow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelRow1ActionPerformed(evt);
            }
        });
        panelEditarTareo1.add(btnDelRow1);

        btnClose1.setText("buttonMaterialIcon1");
        btnClose1.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        btnClose1.setICO_color(defaults.colorPrimaryDark);
        btnClose1.setPreferredSize(new java.awt.Dimension(56, 56));
        btnClose1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClose1ActionPerformed(evt);
            }
        });
        panelEditarTareo1.add(btnClose1);

        btnGuardar1.setText("buttonMaterialIcon1");
        btnGuardar1.setICO(iconfont.MATERIALICON.MATERIALICONIC.SAVE);
        btnGuardar1.setICO_color(defaults.colorPrimaryDark);
        btnGuardar1.setPreferredSize(new java.awt.Dimension(56, 56));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        panelEditarTareo1.add(btnGuardar1);

        btnExportExcel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excelgreen24.png"))); // NOI18N
        btnExportExcel1.setText("buttonMaterialIcon4");
        btnExportExcel1.setPreferredSize(new java.awt.Dimension(56, 56));
        btnExportExcel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcel1ActionPerformed(evt);
            }
        });
        panelEditarTareo1.add(btnExportExcel1);

        panelALL1.setOpaque(false);
        panelALL1.setPreferredSize(new java.awt.Dimension(800, 645));
        panelALL1.setLayout(new java.awt.BorderLayout());

        panelCabecera1.setPreferredSize(new java.awt.Dimension(1116, 52));
        panelCabecera1.setLayout(new java.awt.BorderLayout());

        panelGeneralTareo1.setOpaque(false);
        panelGeneralTareo1.setPreferredSize(new java.awt.Dimension(401, 42));

        etiqueta6.setText("Planilla");
        panelGeneralTareo1.add(etiqueta6);

        buttonMaterialIcon4.setText("buttonMaterialIcon1");
        buttonMaterialIcon4.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon4.setICO_size(18.0F);
        buttonMaterialIcon4.setPreferredSize(new java.awt.Dimension(28, 27));
        buttonMaterialIcon4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon4ActionPerformed(evt);
            }
        });
        panelGeneralTareo1.add(buttonMaterialIcon4);

        cboPlanilla1.setPreferredSize(new java.awt.Dimension(220, 32));
        cboPlanilla1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPlanilla1ActionPerformed(evt);
            }
        });
        panelGeneralTareo1.add(cboPlanilla1);

        jLabel5.setText("      ");
        panelGeneralTareo1.add(jLabel5);

        etiqueta7.setText("Estado  ");
        panelGeneralTareo1.add(etiqueta7);

        cboEstado1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PE", "AP" }));
        cboEstado1.setPreferredSize(new java.awt.Dimension(56, 34));
        cboEstado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEstado1ActionPerformed(evt);
            }
        });
        panelGeneralTareo1.add(cboEstado1);

        jLabel6.setText("       ");
        panelGeneralTareo1.add(jLabel6);

        etiqueta8.setText("Fecha");
        panelGeneralTareo1.add(etiqueta8);

        buttonMaterialIcon5.setText("buttonMaterialIcon1");
        buttonMaterialIcon5.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon5.setICO_size(18.0F);
        buttonMaterialIcon5.setPreferredSize(new java.awt.Dimension(28, 27));
        buttonMaterialIcon5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon5ActionPerformed(evt);
            }
        });
        panelGeneralTareo1.add(buttonMaterialIcon5);

        chooserFecha1.setPreferredSize(new java.awt.Dimension(130, 36));
        chooserFecha1.setWeekOfYearVisible(false);
        panelGeneralTareo1.add(chooserFecha1);

        etiqueta9.setText("      Usuario");
        panelGeneralTareo1.add(etiqueta9);

        buttonMaterialIcon6.setText("buttonMaterialIcon1");
        buttonMaterialIcon6.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon6.setICO_size(18.0F);
        buttonMaterialIcon6.setPreferredSize(new java.awt.Dimension(28, 27));
        buttonMaterialIcon6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon6ActionPerformed(evt);
            }
        });
        panelGeneralTareo1.add(buttonMaterialIcon6);

        cboUser1.setPreferredSize(new java.awt.Dimension(200, 32));
        cboUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUser1ActionPerformed(evt);
            }
        });
        panelGeneralTareo1.add(cboUser1);

        jLabel7.setText("      ");
        panelGeneralTareo1.add(jLabel7);

        button2.setText("Cargar");
        button2.setPreferredSize(new java.awt.Dimension(120, 32));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        panelGeneralTareo1.add(button2);

        jLabel8.setText("             ");
        panelGeneralTareo1.add(jLabel8);

        chkCargaTrab1.setText("Omitir Trabajadores");
        chkCargaTrab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCargaTrab1ActionPerformed(evt);
            }
        });
        panelGeneralTareo1.add(chkCargaTrab1);

        panelCabecera1.add(panelGeneralTareo1, java.awt.BorderLayout.CENTER);

        panelALL1.add(panelCabecera1, java.awt.BorderLayout.NORTH);

        contenedor1.setBackground(new java.awt.Color(255, 255, 255));
        contenedor1.setLayout(new java.awt.CardLayout());

        tablaSubTareos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaSubTareos1.setColumnSelectionAllowed(true);
        tablaSubTareos1.setIsFilterable(false);
        tablaSubTareos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSubTareos1MouseClicked(evt);
            }
        });
        tablaSubTareos1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaSubTareos1KeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tablaSubTareos1);

        javax.swing.GroupLayout panelSubTareos1Layout = new javax.swing.GroupLayout(panelSubTareos1);
        panelSubTareos1.setLayout(panelSubTareos1Layout);
        panelSubTareos1Layout.setHorizontalGroup(
            panelSubTareos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSubTareos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSubTareos1Layout.setVerticalGroup(
            panelSubTareos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSubTareos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor1.add(panelSubTareos1, "card1");

        scrollDistirbucion1.setOpaque(false);

        tabla3.setCOLUMNA("");
        tabla3.setCOLUMNAS(19);
        tabla3.setTAMS(TAM_TRABAJADORES);
        tabla3.setColumnSelectionAllowed(true);
        tabla3.setFocusable(true);
        tabla3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla3MouseClicked(evt);
            }
        });
        tabla3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla3KeyPressed(evt);
            }
        });
        scrollDistirbucion1.setViewportView(tabla3);

        etiqueta10.setESITALIC(true);
        etiqueta10.setFONT_SIZE(17.0F);

        javax.swing.GroupLayout panelDistribucion1Layout = new javax.swing.GroupLayout(panelDistribucion1);
        panelDistribucion1.setLayout(panelDistribucion1Layout);
        panelDistribucion1Layout.setHorizontalGroup(
            panelDistribucion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDistribucion1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta10, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelDistribucion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDistribucion1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollDistirbucion1, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelDistribucion1Layout.setVerticalGroup(
            panelDistribucion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDistribucion1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(495, Short.MAX_VALUE))
            .addGroup(panelDistribucion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDistribucion1Layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(scrollDistirbucion1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        contenedor1.add(panelDistribucion1, "card2");

        panelALL1.add(contenedor1, java.awt.BorderLayout.CENTER);

        paneDashboard1.setMinimumSize(new java.awt.Dimension(880, 48));
        paneDashboard1.setOpaque(false);
        paneDashboard1.setPreferredSize(new java.awt.Dimension(876, 48));
        paneDashboard1.setLayout(new java.awt.GridLayout(1, 5, 20, 0));

        edtPromRendim1.setEditable(false);
        edtPromRendim1.setText("0.0");
        edtPromRendim1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtPromRendim1.setLabel("Promedio Rendim");
        edtPromRendim1.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard1.add(edtPromRendim1);

        edtPromJornal1.setEditable(false);
        edtPromJornal1.setText("0.0");
        edtPromJornal1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtPromJornal1.setLabel("Promedio Jornal");
        edtPromJornal1.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard1.add(edtPromJornal1);

        edtMinimo1.setEditable(false);
        edtMinimo1.setText("0.0");
        edtMinimo1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtMinimo1.setLabel("Minimo");
        edtMinimo1.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard1.add(edtMinimo1);

        edtMaximo1.setEditable(false);
        edtMaximo1.setText("0.0");
        edtMaximo1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtMaximo1.setLabel("Maximo");
        edtMaximo1.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard1.add(edtMaximo1);

        edtTotalPlantas1.setEditable(false);
        edtTotalPlantas1.setText("0.0");
        edtTotalPlantas1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtTotalPlantas1.setLabel("Total Plantas");
        edtTotalPlantas1.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard1.add(edtTotalPlantas1);

        edtTotalTrab1.setEditable(false);
        edtTotalTrab1.setText("0.0");
        edtTotalTrab1.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        edtTotalTrab1.setLabel("Total Trabajadores");
        edtTotalTrab1.setModo(kevin.component.edittext.EditText.MODO.NUMEROS_DECIMAL);
        paneDashboard1.add(edtTotalTrab1);

        panelALL1.add(paneDashboard1, java.awt.BorderLayout.PAGE_END);

        jScrollPane3.setViewportView(panelALL1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(panelEditarTareo1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(623, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEditarTareo1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Edicion por SubTareos", jPanel3);

        jInternalFrame1.getContentPane().add(jTabbedPane2, java.awt.BorderLayout.CENTER);

        jMenu3.setText("Configuracion");

        chkConfigSubTareos1.setSelected(true);
        chkConfigSubTareos1.setText("Respetar Configuracion de SubTareos");
        jMenu3.add(chkConfigSubTareos1);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Buscar");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Buscar Trabajador");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuBar2.add(jMenu4);

        jInternalFrame1.setJMenuBar(jMenuBar2);

        getContentPane().add(jInternalFrame1, java.awt.BorderLayout.PAGE_START);

        jMenu1.setText("Configuracion");

        chkConfigSubTareos.setSelected(true);
        chkConfigSubTareos.setText("Respetar Configuracion de SubTareos");
        jMenu1.add(chkConfigSubTareos);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Buscar");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Buscar Trabajador");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDistribucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDistribucionActionPerformed
        if (chkCargaTrab.isSelected()) {
            Toast.makeText((JFrame) Frame, "No se han cargado Trabajadores", Toast.Style.ERROR).display();
        } else {
            cargarDistribucion();
        }
    }//GEN-LAST:event_btnDistribucionActionPerformed

    private void editSubTareo() {
        int[] selected = tablaSubTareos.getSelectedRows();
        switch (positionContainer) {
            //VALIDAR LA EDICION DE LOS SUBTAREOS
            case 1:
                switch (tablaSubTareos.getSelectedColumn()) {
                    case 8://BUSCNADO CULTIVOS Y VARIEDADES
                        if (!jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
                            JDialog.setDefaultLookAndFeelDecorated(false);
                            JMethods.settingGlassPane((JFrame) Frame, buscarCultivoVariedad, principalvalues.colorAccent, 0.6f);
                            if (buscarCultivoVariedad.DATA_SELECT != null) {
                                for (int i = 0; i < selected.length; i++) {
                                    tablaSubTareos.setValueAt(buscarCultivoVariedad.DATA_SELECT[6].toString(), selected[i], 8);
                                    tablaSubTareos.setValueAt(buscarCultivoVariedad.DATA_SELECT[3].toString(), selected[i], 9);
                                    tablaSubTareos.setValueAt(buscarCultivoVariedad.DATA_SELECT[5].toString(), selected[i], 10);
                                    if (tablaSubTareos.getValueAt(selected[i], tablaSubTareos.getColumnCount() - 1).toString().equals("0"))
                                        tablaSubTareos.setValueAt("1", selected[i], tablaSubTareos.getColumnCount() - 1);

                                }
                                buscarCultivoVariedad.DATA_SELECT = null;
                                setEditDelete();

                            }
                            JDialog.setDefaultLookAndFeelDecorated(true);
                        }
                        break;
                    case 13://BUSCANDO ACTIVIDADES Y LABOR
                        if (!jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
                            JDialog.setDefaultLookAndFeelDecorated(false);
                            initActividadLabor(chkConfigSubTareos.isSelected() ? tablaSubTareos.getValueAt(ROW1, 8).toString().split("_")[0] : "", chkConfigSubTareos.isSelected() ? tablaSubTareos.getValueAt(ROW1, 8).toString().split("_")[1] : "");
                            JMethods.settingGlassPane((JFrame) Frame, buscarLaborActividad, principalvalues.colorAccent, 0.6f);
                            if (buscarLaborActividad.DATA_SELECT != null) {
                                for (int i = 0; i < selected.length; i++) {
                                    tablaSubTareos.setValueAt(buscarLaborActividad.DATA_SELECT[2].toString(), selected[i], 11);
                                    tablaSubTareos.setValueAt(buscarLaborActividad.DATA_SELECT[3].toString(), selected[i], 12);
                                    tablaSubTareos.setValueAt(buscarLaborActividad.DATA_SELECT[0].toString(), selected[i], 13);
                                    tablaSubTareos.setValueAt(buscarLaborActividad.DATA_SELECT[1].toString(), selected[i], 14);
                                    if (tablaSubTareos.getValueAt(selected[i], tablaSubTareos.getColumnCount() - 1).toString().equals("0"))
                                        tablaSubTareos.setValueAt("1", selected[i], tablaSubTareos.getColumnCount() - 1);
                                }
                                buscarLaborActividad.DATA_SELECT = null;
                                setEditDelete();
                            }
                            JDialog.setDefaultLookAndFeelDecorated(true);
                        }
                        break;
                    case 15://BUSCNADO CONUSMIDORES
                        if (!jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
                            JDialog.setDefaultLookAndFeelDecorated(false);
                            initCOnsumidor(
                                    chkConfigSubTareos.isSelected() ? tablaSubTareos.getValueAt(ROW1, 8).toString().split("_")[0] : "",
                                    chkConfigSubTareos.isSelected() ? tablaSubTareos.getValueAt(ROW1, 8).toString().split("_")[1] : "",
                                    chkConfigSubTareos.isSelected() ? tablaSubTareos.getValueAt(ROW1, 11).toString() : "",
                                    chkConfigSubTareos.isSelected() ? tablaSubTareos.getValueAt(ROW1, 13).toString() : ""
                            );

                            JMethods.settingGlassPane((JFrame) Frame, buscarConsumidores, principalvalues.colorAccent, 0.6f);

                            if (buscarConsumidores.DATA_SELECT != null) {
                                for (int i = 0; i < selected.length; i++) {
                                    tablaSubTareos.setValueAt(buscarConsumidores.DATA_SELECT[0].toString(), selected[i], 15);
                                    tablaSubTareos.setValueAt(buscarConsumidores.DATA_SELECT[1].toString(), selected[i], 16);
                                    if (tablaSubTareos.getValueAt(selected[i], tablaSubTareos.getColumnCount() - 1).toString().equals("0"))
                                        tablaSubTareos.setValueAt("1", selected[i], tablaSubTareos.getColumnCount() - 1);
                                }
                                buscarConsumidores.DATA_SELECT = null;
                                setEditDelete();
                            }
                            JDialog.setDefaultLookAndFeelDecorated(true);
                        }
                        break;
                }
                break;
            //VALIDAR LA EDICION DE LAS DISTRIBUCION DE TRABAJADORES
            case 2:
                updateDistribucion();
                break;
        }
    }
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editSubTareo();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void updateDistribucion() {
        if (tabla2.getSelectedColumn() != 1) {
            int[] sel = this.tabla2.getSelectedRows();
            switch (tabla2.getSelectedColumn()) {
                case 5:
                case 6:
                case 7:
                case 8:
                    if (!jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
                        String x = JOptionPane.showInputDialog(this.getContentPane(), "Ingrese el Nuevo Horario");
                        if (x != null) {
                            for (int i = 0; i < sel.length; i++) {
                                tabla2.setValueAt(x, sel[i], tabla2.getSelectedColumn());
                                if (tabla2.getValueAt(sel[i], tabla2.getColumnCount() - 1).toString().equals("0"))
                                    tabla2.setValueAt("1", sel[i], tabla2.getColumnCount() - 1);
                            }
                            setEditDelete();
                        }
                    }
                    break;
                case 22:
                    PDialogInputArea.PDialogShow(Frame, "Modifique las Observaciones",
                            () -> {
                                String obs = PDialogInputArea.input;
                                if (!obs.isEmpty()) {
                                    for (int i = 0; i < sel.length; i++) {
                                        tabla2.setValueAt(obs, sel[i], tabla2.getSelectedColumn());
                                        if (tabla2.getValueAt(sel[i], tabla2.getColumnCount() - 1).toString().equals("0"))
                                            tabla2.setValueAt("1", sel[i], tabla2.getColumnCount() - 1);
                                    }
                                    setEditDelete();
                                }
                            },
                            () -> {
                                System.out.println("");
                            });
                    break;
                case 9:
                    if (!jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
                        JDialog.setDefaultLookAndFeelDecorated(false);
                        JMethods.settingGlassPane((JFrame) Frame, buscarMotivos, principalvalues.colorAccent, 0.6f);
                        if (buscarMotivos.DATA_SELECT != null) {
                            for (int i = 0; i < sel.length; i++) {
                                tabla2.setValueAt(buscarMotivos.DATA_SELECT[0].toString(), sel[i], 7);
                                tabla2.setValueAt(buscarMotivos.DATA_SELECT[1].toString(), sel[i], 8);
                                if (tabla2.getValueAt(sel[i], tabla2.getColumnCount() - 1).toString().equals("0"))
                                    tabla2.setValueAt("1", sel[i], tabla2.getColumnCount() - 1);
                            }
                            buscarMotivos.DATA_SELECT = null;
                            setEditDelete();
                        }
                        JDialog.setDefaultLookAndFeelDecorated(true);
                    }
                    break;
                case 11:
                case 12:
                    if (!jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
                        int a = JOptionPane_methods.Input_Integer((Component) this.getContentPane(), (String) "Ingrese el Nuevo Valor");
                        if (a >= 0) {
                            for (int i = 0; i < sel.length; i++) {

                                if (a == 0 && tabla2.getSelectedColumn() == 11) {
                                    tabla2.setValueAt("0", sel[i], 11);
                                    tabla2.setValueAt("1", sel[i], 12);
                                } else if (a == 0 && tabla2.getSelectedColumn() == 12) {
                                    tabla2.setValueAt("1", sel[i], 9);
                                    tabla2.setValueAt("0", sel[i], 12);
                                } else if (a == 1 && tabla2.getSelectedColumn() == 11) {
                                    tabla2.setValueAt("1", sel[i], 11);
                                    tabla2.setValueAt("0", sel[i], 10);
                                } else if (a == 1 && tabla2.getSelectedColumn() == 12) {
                                    tabla2.setValueAt("0", sel[i], 11);
                                    tabla2.setValueAt("1", sel[i], 12);
                                }
                                if (tabla2.getValueAt(sel[i], tabla2.getColumnCount() - 1).toString().equals("0"))
                                    tabla2.setValueAt("1", sel[i], tabla2.getColumnCount() - 1);
                            }
                            setEditDelete();
                        }
                    }

                    break;
                case 14://RENDIM
                    double bx = JOptionPane_methods.Input_Double((Component) this.getContentPane(), (String) "Esta a punto de Modificar los Siguientes Items\nIngrese el Nuevo Valor");
                    if (bx >= 0) {
                        for (int i = 0; i < sel.length; i++) {
                            tabla2.setValueAt(bx, sel[i], tabla2.getSelectedColumn());
                            if (tabla2.getValueAt(sel[i], tabla2.getColumnCount() - 1).toString().equals("0"))
                                tabla2.setValueAt("1", sel[i], tabla2.getColumnCount() - 1);
                        }
                        setEditDelete();
                    }
                    break;
                case 13://JORNAL
                case 15://AVANCE
                case 16://JORNO
                case 17://JOR_COMP               
                case 21://BONO
                    if (!jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
                        double b = JOptionPane_methods.Input_Double((Component) this.getContentPane(), (String) "Esta a punto de Modificar los Siguientes Items\nIngrese el Nuevo Valor");
                        if (b >= 0) {
                            for (int i = 0; i < sel.length; i++) {
                                tabla2.setValueAt(b, sel[i], tabla2.getSelectedColumn());
                                if (tabla2.getValueAt(sel[i], tabla2.getColumnCount() - 1).toString().equals("0"))
                                    tabla2.setValueAt("1", sel[i], tabla2.getColumnCount() - 1);
                            }
                            setEditDelete();
                        }
                    }
                    break;    
                case 23:    
                    if (!jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
                     JOptionPane_methods.MostrarConfirmacion(Frame, (Window frame1) -> {                        
                            for (int i = 0; i < sel.length; i++) {                                    
                                if ( tabla2.getSelectedColumn() == 23) {
                                    String d = tabla2.getValueAt( sel[i], 23).toString();
                                    d = (d.equals("0"))?"1":"0";
                                    tabla2.setValueAt(d, sel[i], 23);                                    
                                } 
                            }
                            setEditDelete();                            
                                    }, "¿Desea cambiar el valor del día siguiente?");   
                    }
                    break;
            }

        }
    }


    private void tabla2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla2MouseClicked
        ROW2 = tabla2.getSelectedRow();
        if (PUEDE_EDITAR && !jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
            if (evt.getClickCount() == 2) {
                if (tabla2.getSelectedColumn() == 1) {
                    JDialog.setDefaultLookAndFeelDecorated(false);
                    JMethods.settingGlassPane((JFrame) Frame, buscarTrabajadores, principalvalues.colorAccent, 0.6f);
                    if (buscarTrabajadores.DATA_SELECT != null) {
                        tabla2.setValueAt(buscarTrabajadores.DATA_SELECT[2].toString(), ROW2, 1);
                        tabla2.setValueAt(buscarTrabajadores.DATA_SELECT[3].toString(), ROW2, 2);
                        buscarTrabajadores.DATA_SELECT = null;
                        if (tabla2.getValueAt(ROW2, tabla2.getColumnCount() - 1).toString().equals("0"))
                            tabla2.setValueAt("1", ROW2, tabla2.getColumnCount() - 1);
                        setEditDelete();
                    }
                    JDialog.setDefaultLookAndFeelDecorated(true);
                }
            }
        }
    }//GEN-LAST:event_tabla2MouseClicked
    private void CancelAll() {
        JOptionPane_methods.MostrarConfirmacion(Frame, (Window frame) -> {
            switch (positionContainer) {
                case 1:
                    hizeCambios1 = false;
                    estaEliminando = false;
                    LISTACHANGES.clear();
                    LISTAADD.clear();
                    LISTADELETES.clear();
                    apply(1);
                    cargarSubTareos();
                    break;
                case 2:
                    hizeCambios1 = false;
                    estaEliminando = false;
                    LISTAADD.clear();
                    LISTACHANGES.clear();
                    LISTADELETES.clear();
                    apply(1);
                    break;
            }
        },
                "Esta seguro de Cancelar???, los cambios no se Guardaran");
    }
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        CancelAll();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void cargarDistribucion() {
        tabla2.setComponentPopupMenu(jPopupMenu1);
        DATAD = JMethods.StringTOData(tablaSubTareos.getValueAt(ROW1, tablaSubTareos.getColumnCount() - 2).toString(), TAM_TRABAJADORES.length);
        paneDashboard.setVisible(true);
        etiqueta1.setText("SubT: "
                + tablaSubTareos.getValueAt(ROW1, 1).toString() + " |               "
                + tablaSubTareos.getValueAt(ROW1, 11).toString()
                + "(" + tablaSubTareos.getValueAt(ROW1, 12).toString() + ")          "
                + tablaSubTareos.getValueAt(ROW1, 13).toString()
                + "(" + tablaSubTareos.getValueAt(ROW1, 14).toString() + ")          "
                + tablaSubTareos.getValueAt(ROW1, 15).toString()
                + "(" + tablaSubTareos.getValueAt(ROW1, 16).toString() + ")"
        );
        apply(2);
        panelDistribucion.repaint();
        tabla2.revalidate();
        tabla2.repaint();
        tabla2.updateUI();
        this.tablemodelTRABAJADORES = new JMTableModel();
        this.tablemodelTRABAJADORES.setDataVector(this.DATAD, this.TITLES_TRABAJADORES);
        this.tabla2.setModel((TableModel) this.tablemodelTRABAJADORES);
        this.tabla2.setTAMS(this.TAM_TRABAJADORES);
        this.tabla2.setTITLES(this.TITLES_TRABAJADORES);
        this.tabla2.staticWidthColumn();
//        this.tabla2.setDefaultRenderer(Object.class, new MyTableCellRender(tabla2.getColumnCount() - 1));
        this.tabla2.setDefaultRenderer(Object.class, new FormatDistribucion());
        TableRowUtilities.addNumberColumn(tabla2, 1, true);
        JMethods.updateInternalJTable(this, tabla2);

    }

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        switch (positionContainer) {
            case 1:
                if (JOptionPane.showConfirmDialog(this, "Esta seguro de Eliminar los items seleccionados?\n"
                        + "Recuerde que si Elimina el presente SubTareo tambien se eliminaran los trabajadores que se encuentren asignados a este.", "Eliminando SubTareos", 0) == 0) {
                    estaEliminando = true;
                    int[] sel = this.tablaSubTareos.getSelectedRows();
                    for (int i = 0; i < sel.length; i++) {
                        LISTADELETES.add(new Object[]{
                            tablaSubTareos.getValueAt(sel[i], 0),
                            tablaSubTareos.getValueAt(sel[i], 1)
                        });
                    }
                    DefaultTableModel def = (DefaultTableModel) tablaSubTareos.getModel();

                    for (int i = sel.length - 1; i >= 0; i--) {
                        try {
                            def.removeRow(sel[i]);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        tablaSubTareos.revalidate();
                    }
                    btnEditar.setVisible(false);
                    btnAgregar.setVisible(false);
                    btnDelRow.setVisible(false);
                    btnGuardar.setVisible(true);
                }
                break;
            case 2:
                if (JOptionPane.showConfirmDialog(this, "Esta seguro de Eliminar los presentes Trabajadores???", "Eliminando Trabajadores", 0) == 0) {
                    estaEliminando = true;
                    int[] sel = this.tabla2.getSelectedRows();
                    for (int i = 0; i < sel.length; i++) {
                        if (tabla2.getValueAt(sel[i], tabla2.getColumnCount() - 1).toString().equals("0")
                                || tabla2.getValueAt(sel[i], tabla2.getColumnCount() - 1).toString().equals("1")) {
                            LISTADELETES.add(new Object[]{tabla2.getValueAt(sel[i], 0),
                                                          tabla2.getValueAt(sel[i], 1),
                                                          tabla2.getValueAt(sel[i], 3),
                                                          tabla2.getValueAt(sel[i], 4)
                            });
                        } else {
                            System.out.println("Eliminando del Registro pero no de BD porque era un Agregado");
                        }

                    }
                    DefaultTableModel def = (DefaultTableModel) tabla2.getModel();

                    for (int i = sel.length - 1; i >= 0; i--) {
                        try {
                            def.removeRow(sel[i]);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        tabla2.revalidate();
                    }
                    btnEditar.setVisible(false);
                    btnAgregar.setVisible(false);
                    btnGuardar.setVisible(true);
                }
                break;
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void tabla2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla2KeyPressed
        if (PUEDE_EDITAR) {
            if (evt.getKeyCode() == KeyEvent.VK_E) {
                updateDistribucion();
            }

            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                eliminarRow();
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                agregarTrab();
            }
        }

    }//GEN-LAST:event_tabla2KeyPressed

    public void agregarTrab() {
        hizeCambios2 = true;
        switch (positionContainer) {
            case 1:
//                JDialog.setDefaultLookAndFeelDecorated(false);
                if (cboUser.getSelectedItem().toString().length() == 0) {
                    JOptionPane.showMessageDialog(this, "No se puede agregar un Tareo, se necesita seleccionar un solo Usuario");
                } else {
                    NuevoSubTareoOnLine cfgTrabajadores = new NuevoSubTareoOnLine(true, (JFrame) Frame, IDTAREO, tablaSubTareos.getValueAt(tablaSubTareos.getRowCount() - 1, 1).toString(), () -> {
                        cargarSubTareos();
                    });
                    JMethods.settingGlassPane((JFrame) Frame, cfgTrabajadores, MaterialColor.BLUEGREY_700, 0.6f);
                }
                break;
            case 2:
                if (tabla2.getRowCount() > 0) {
                    tablemodelTRABAJADORES.addRow(new Object[]{
                        tabla2.getValueAt(0, 0),
                        JMethods.get_id(8).toUpperCase(),
                        "Trabajador Unknow",
                        tabla2.getValueAt(0, 3),
                        JMethods.completar_item(Integer.parseInt(tabla2.getValueAt(tabla2.getRowCount() - 1, 4).toString()) + 1),
                        "00:00:00",
                        "00:00:00",
                        "00:00:00",
                        "00:00:00",
                        "-",
                        "-",
                        1,
                        0,
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        "-",
                        "-",
                        0.0,
                        "...",
                        0,
                        2
                    });
                    estaAgregando = true;
                    btnDel.setVisible(false);
                    btnDelRow.setVisible(true);
                }
                break;
        }
    }

    private void eliminarRow() {
        if (JOptionPane.showConfirmDialog(this, "Esta seguro de Eliminar los presentes Trabajadores???", "Eliminando Trabajadores", 0) == 0) {

            int[] sel = this.tabla2.getSelectedRows();
            DefaultTableModel def = (DefaultTableModel) tabla2.getModel();
            for (int i = sel.length - 1; i >= 0; i--) {
                if (tabla2.getValueAt(sel[i], tabla2.getColumnCount() - 1).toString().equals("2")) {
                    try {
                        def.removeRow(sel[i]);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                tabla2.revalidate();
            }
        }
    }
    private void btnDelRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelRowActionPerformed
        eliminarRow();
    }//GEN-LAST:event_btnDelRowActionPerformed

    private void cboUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUserActionPerformed
        if (cboUser.getItemCount() > 0) {
            IDUSUARIO = cboUser.getSelectedItem().toString();
        } else {
            IDUSUARIO = "";
        }
    }//GEN-LAST:event_cboUserActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        switch (positionContainer) {
            case 1:
                if (estaEliminando) {
                    eliminarCambios(JMethods.getDETALLE_Object_XML("dtareo", "item", LISTADELETES));
                } else {
                    if (hizeCambios1) {
                        for (int i = 0; i < tablaSubTareos.getRowCount(); i++) {
                            if (tablaSubTareos.getValueAt(i, tablaSubTareos.getColumnCount() - 1).toString().equals("1")) {
                                LISTACHANGES.add(new Object[]{
                                    tablaSubTareos.getValueAt(i, 0),//IDTAREO
                                    tablaSubTareos.getValueAt(i, 1),//ITEM
                                    tablaSubTareos.getValueAt(i, 8),//IDCULTIVOVARIEDAD
                                    tablaSubTareos.getValueAt(i, 11),//IDACTIVIDAD
                                    tablaSubTareos.getValueAt(i, 13),//IDLABOR
                                    tablaSubTareos.getValueAt(i, 15)});//IDCONSUMIDOR
                            }
                        }
                        ArrayList<String[]> LISTC = new ArrayList<>();
                        for (int i = 0; i < tablaSubTareos.getRowCount(); i++) {
                            LISTC.add(new String[]{
                                tablaSubTareos.getValueAt(i, 0).toString(),//IDTAREO
                                tablaSubTareos.getValueAt(i, 1).toString(),//ITEM
                                tablaSubTareos.getValueAt(i, 8).toString(),//IDCULTIVOVARIEDAD
                                tablaSubTareos.getValueAt(i, 11).toString(),//IDACTIVIDAD
                                tablaSubTareos.getValueAt(i, 13).toString(),//IDLABOR
                                tablaSubTareos.getValueAt(i, 15).toString()
                            });//IDCONSUMIDOR
                        }

                        Object[] dat = JMethods.validarSubTareoDuplicado(LISTC);
                        System.out.println(Arrays.toString(dat));

                        if (!((boolean) dat[0])) {
                            String DETALLEENVIAR = JMethods.getDETALLE_Object_XML("dtareo", "item", LISTACHANGES);
                            System.out.println(DETALLEENVIAR);
                            aplicarCambios(DETALLEENVIAR, "UpSubTareoSmart2020");
                        } else {
                            Toast.makeText((JFrame) Frame, "Existen SubTareos Duplicados: " + dat[1].toString(), Toast.Style.ERROR, Toast.LENGTH_LONG).display();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No encontramos Modificaciones 1");
                    }
                }
                break;
            case 2:
                if (estaEliminando) {
                    eliminarCambios(JMethods.getDETALLE_Object_XML("ddtareo", "item", LISTADELETES));
                } else {
                    if (hizeCambios2) {
                        for (int i = 0; i < tabla2.getRowCount(); i++) {
                            switch (tabla2.getValueAt(i, tabla2.getColumnCount() - 1).toString()) {
                                case "1":
                                    LISTACHANGES.add(new Object[]{
                                        tabla2.getValueAt(i, 0),// idtareo
                                            tabla2.getValueAt(i, 3),// itemid
                                            tabla2.getValueAt(i, 4),// item
                                            tabla2.getValueAt(i, 1),// idtrabajador
                                            tabla2.getValueAt(i, 11),// esjornal
                                            tabla2.getValueAt(i, 12),// esrendimiento
                                            tabla2.getValueAt(i, 5),// inicio
                                            tabla2.getValueAt(i, 7),// inicio_ref
                                            tabla2.getValueAt(i, 8),// fi_ref
                                            tabla2.getValueAt(i, 6),//fin
                                            tabla2.getValueAt(i, 13),// jornal
                                            tabla2.getValueAt(i, 14),// rendimiento
                                            tabla2.getValueAt(i, 15),// avance
                                            tabla2.getValueAt(i, 16),// JORNAL_NO
                                            tabla2.getValueAt(i, 17),//JOR_COMP
                                            tabla2.getValueAt(i, 18),// rendimientoextra
                                            tabla2.getValueAt(i, 20),// conceptobono
                                            tabla2.getValueAt(i, 21),// bono
                                            tabla2.getValueAt(i, 9),// idmotivo
                                            tabla2.getValueAt(i, 22),// observaciones
                                            tabla2.getValueAt(i, 23)// diasiguiente
                                    });
                                    break;
                                case "2":
                                    if (estaAgregando)
                                        LISTAADD.add(new Object[]{
                                            tabla2.getValueAt(i, 0),// idtareo
                                            tabla2.getValueAt(i, 3),// itemid
                                            tabla2.getValueAt(i, 4),// item
                                            tabla2.getValueAt(i, 1),// idtrabajador
                                            tabla2.getValueAt(i, 11),// esjornal
                                            tabla2.getValueAt(i, 12),// esrendimiento
                                            tabla2.getValueAt(i, 5),// inicio
                                            tabla2.getValueAt(i, 7),// inicio_ref
                                            tabla2.getValueAt(i, 8),// fi_ref
                                            tabla2.getValueAt(i, 6),//fin
                                            tabla2.getValueAt(i, 13),// jornal
                                            tabla2.getValueAt(i, 14),// rendimiento
                                            tabla2.getValueAt(i, 15),// avance
                                            tabla2.getValueAt(i, 16),// JORNAL_NO
                                            tabla2.getValueAt(i, 17),//JOR_COMP
                                            tabla2.getValueAt(i, 18),// rendimientoextra
                                            tabla2.getValueAt(i, 20),// conceptobono
                                            tabla2.getValueAt(i, 21),// bono
                                            tabla2.getValueAt(i, 9),// idmotivo
                                            tabla2.getValueAt(i, 22),// observaciones
                                            tabla2.getValueAt(i, 23)// diasiguiente
                                            
                                        });
                                    break;
                            }

                        }

                        String DETALLEENVIAR = JMethods.getDETALLE_Object_XML("ddtareo", "item", LISTACHANGES);
                        System.out.println(DETALLEENVIAR);

                        aplicarCambios(DETALLEENVIAR, "UpTrabajadoresSmart2022");
                    } else {
                        JOptionPane.showMessageDialog(this, "No encontramos Modificaciones 2");
                    }
                }
                break;
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        JOptionPane_methods.MostrarConfirmacion(Frame, (Window frame) -> {
            agregarTrab();
        }, "Esta seguro de Agregar un Nuevo Trabajador a este SubTareo???");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void cboPlanillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPlanillaActionPerformed
        if (cboPlanilla.getItemCount() > 0) {
            System.out.println("--------------------------------->>>>>>>>>>>>>>> SELECTED INDEX: " + cboPlanilla.getSelectedIndex());
            IDPLANILLA = cboPlanilla.getIditem().toString();
            System.out.println("NUEVA PLANILLA : " + IDPLANILLA);
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
                    false
            );
            buscarTrabajadores.setTitle("Buscar Trabajadores");
        } else {
            IDPLANILLA = "";
        }
    }//GEN-LAST:event_cboPlanillaActionPerformed

    private void showRpt() {
        if (jkeys.IDCLIENTE.equals("20554556192")) {

            edtPromRendim.setText(tablaSubTareos.getValueAt(tablaSubTareos.getSelectedRow(), 6).toString());
            edtPromJornal.setText(tablaSubTareos.getValueAt(tablaSubTareos.getSelectedRow(), 7).toString());
            edtMinimo.setVisible(false);
            edtMaximo.setVisible(false);
            edtTotalPlantas.setText(tablaSubTareos.getValueAt(tablaSubTareos.getSelectedRow(), 3).toString());
            edtTotalTrab.setText(tablaSubTareos.getValueAt(tablaSubTareos.getSelectedRow(), 4).toString());
        }
    }
    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        cargarSubTareos();
    }//GEN-LAST:event_button1ActionPerformed

    private void buttonMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon1ActionPerformed
//        cboUser.setSelectedIndex(0);
        IDUSUARIO = "";
    }//GEN-LAST:event_buttonMaterialIcon1ActionPerformed

    private void buttonMaterialIcon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon2ActionPerformed
//        cboPlanilla.setSelectedIndex(0);
        IDPLANILLA = "";
    }//GEN-LAST:event_buttonMaterialIcon2ActionPerformed

    private void buttonMaterialIcon3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon3ActionPerformed
        chooserFecha.setDate(null);
    }//GEN-LAST:event_buttonMaterialIcon3ActionPerformed

    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed
        export_excel();
    }//GEN-LAST:event_btnExportExcelActionPerformed

    private void cboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEstadoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        switch (tabla2.getSelectedColumn()) {
            case 1:
                JMethods.buscarTablaColumna("Ingrese el Dni del Trabajador", tabla2, 1);
                break;
            case 2:
                JMethods.buscarTablaColumna("Ingrese el Nombre del Trabajador", tabla2, 2);
                break;
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void sm_corregirdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sm_corregirdniActionPerformed
        if (!jvalues.USUARIO.getTipousuario().getIdtipousuario().equals("SUPERCAMP_AVA")) {
            String x = JOptionPane.showInputDialog(this.getContentPane(), "Ingrese el Nuevo Valor");
            if (x != null) {
                tabla2.setValueAt(x, tabla2.getSelectedRow(), tabla2.getSelectedColumn());
                if (tabla2.getValueAt(tabla2.getSelectedRow(), tabla2.getColumnCount() - 1).toString().equals("0"))
                    tabla2.setValueAt("1", tabla2.getSelectedRow(), tabla2.getColumnCount() - 1);
                setEditDelete();
            }
        }
    }//GEN-LAST:event_sm_corregirdniActionPerformed

    private void chkCargaTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCargaTrabActionPerformed

    }//GEN-LAST:event_chkCargaTrabActionPerformed

    private void tablaSubTareosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaSubTareosKeyPressed
        if (PUEDE_EDITAR)
            if (evt.getKeyCode() == KeyEvent.VK_E)
                editSubTareo();
    }//GEN-LAST:event_tablaSubTareosKeyPressed

    private void tablaSubTareosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSubTareosMouseClicked
        if (tablaSubTareos.getRowCount() > 0) {

            ROW1 = tablaSubTareos.getSelectedRow();
            IDTAREO = tablaSubTareos.getValueAt(ROW1, 0).toString();
            ITEM = tablaSubTareos.getValueAt(ROW1, 1).toString();

            if (!chkCargaTrab.isSelected()) {
                if (!PUEDE_EDITAR) {
                    btnAgregar.setVisible(false);
                } else {
                    if (ES_GENERAL)
                        btnAgregar.setVisible(!cboUser.getSelectedItem().toString().isEmpty());
                }
                if (!hizeCambios1) {
                    btnDistribucion.setVisible(true);
                    if (evt.getClickCount() == 2 && tablaSubTareos.getValueAt(ROW1, tablaSubTareos.getColumnCount() - 2).toString().length() > 0)
                        if (chkCargaTrab.isSelected()) {
                            Toast.makeText((JFrame) Frame, "No se han cargado Trabajadores ", Toast.Style.ERROR).display();
                        } else {
                            cargarDistribucion();
                        }
                }
            }
            showRpt();

        }
    }//GEN-LAST:event_tablaSubTareosMouseClicked

    private void btnDistribucion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDistribucion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDistribucion1ActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregar1ActionPerformed

    private void btnDel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDel1ActionPerformed

    private void btnDelRow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelRow1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelRow1ActionPerformed

    private void btnClose1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClose1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClose1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnExportExcel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportExcel1ActionPerformed

    private void buttonMaterialIcon4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonMaterialIcon4ActionPerformed

    private void cboPlanilla1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPlanilla1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPlanilla1ActionPerformed

    private void cboEstado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEstado1ActionPerformed

    private void buttonMaterialIcon5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonMaterialIcon5ActionPerformed

    private void buttonMaterialIcon6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonMaterialIcon6ActionPerformed

    private void cboUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUser1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUser1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button2ActionPerformed

    private void chkCargaTrab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCargaTrab1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCargaTrab1ActionPerformed

    private void tablaSubTareos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSubTareos1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaSubTareos1MouseClicked

    private void tablaSubTareos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaSubTareos1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaSubTareos1KeyPressed

    private void tabla3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla3MouseClicked

    private void tabla3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla3KeyPressed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    public void aplicarCambios(String detalleChanges, String procedure) {
        if (!LISTACHANGES.isEmpty()) {

            ExecHTTP.ExecPostProcedure(Frame,
                    gettin_pages.api_set(),
                    new String[]{"iddatabase2", "query"},
                    new Object[]{
                        jkeys.IDDATABASE2,
                        ExecHTTP.parseQL("exec " + procedure,
                                jkeys.IDDATABASE, jkeys.IDEMPRESA, IDPLANILLA, FECHA_DATE, jkeys.IDUSUARIO, detalleChanges, INFO_HOST, INFO_HOST
                        )
                    },
                    () -> {//ACTION DONE
                        if (!estaAgregando && LISTAADD.isEmpty()) {
                            apply(1);
                            cargarSubTareos();
                            LISTACHANGES.clear();
                            LISTADELETES.clear();
                            LISTADELETES.clear();
                            estaEliminando = false;
                            estaAgregando = false;
                            hizeCambios1 = false;
                            hizeCambios2 = false;
                            if (!chkCargaTrab.isSelected())
                                cargarDistribucion();
                        } else if (estaAgregando && !LISTAADD.isEmpty()) {
                            agregarCambios();
                        }
                    },
                    () -> {//ACTION WARN
                    });
        } else {
            agregarCambios();
        }
    }

    public void agregarCambios() {
        if (!LISTAADD.isEmpty()) {
            String DETALLE_AGREGAR = JMethods.getDETALLE_Object_XML("ddtareo", "item", LISTAADD);

            ExecHTTP.ExecPostProcedure(Frame,
                    gettin_pages.api_set(),
                    new String[]{"iddatabase2", "query"},
                    new Object[]{
                        jkeys.IDDATABASE2,
                        ExecHTTP.parseQL("exec UpNuevosTrabajadores2022 ",
                                new Object[]{
                                    jkeys.IDDATABASE,
                                    jkeys.IDEMPRESA,
                                    DETALLE_AGREGAR,
                                    (isNisira ? 1 : 0),
                                    IDTAREO,
                                    INFO_HOST,
                                    INFO_HOST,
                                    jkeys.IDUSUARIO
                                }
                        )
                    },
                    () -> {//ACTION DONE
                        apply(1);
                        cargarSubTareos();
                        tablaSubTareos.setRowSelectionInterval(ROW1, ROW1);
                        cargarDistribucion();
                        System.out.println(DETALLE_AGREGAR);
                    },
                    () -> {//ACTION WARN
                        CancelAll();
                    });

            LISTACHANGES.clear();
            LISTAADD.clear();
            LISTADELETES.clear();
            estaEliminando = false;
            estaAgregando = false;
            hizeCambios1 = false;
            hizeCambios2 = false;

        } else {
            System.out.println("No se encontraron datos para Agregar");            
        }
    }

    public void eliminarCambios(String detalleEliminar) {
        if (!LISTADELETES.isEmpty()) {

            ExecHTTP.ExecPostProcedure(Frame,
                    gettin_pages.api_set(),
                    new String[]{"iddatabase2", "query"},
                    new Object[]{
                        jkeys.IDDATABASE2,
                        ExecHTTP.parseQL("exec " + (positionContainer == 1 ? "DelSubTareo " : "DelTrabajadores "),
                                jkeys.IDDATABASE, 
                                jkeys.IDEMPRESA, 
                                IDPLANILLA, 
                                FECHA_DATE, 
                                jkeys.IDUSUARIO, 
                                detalleEliminar, 
                                INFO_HOST, 
                                INFO_HOST
                        )
                    },
                    () -> {//ACTION DONE
                        apply(1);
                        cargarSubTareos();
                    },
                    () -> {//ACTION WARN
                    });
            LISTACHANGES.clear();
            LISTAADD.clear();
            LISTADELETES.clear();
            estaEliminando = false;
            hizeCambios1 = false;
            hizeCambios2 = false;
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron Modificaciones para Eliminar");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.ButtonMaterialIcon btnAgregar;
    private kevin.component.button.ButtonMaterialIcon btnAgregar1;
    private kevin.component.button.ButtonMaterialIcon btnClose;
    private kevin.component.button.ButtonMaterialIcon btnClose1;
    private kevin.component.button.ButtonMaterialIcon btnDel;
    private kevin.component.button.ButtonMaterialIcon btnDel1;
    private kevin.component.button.ButtonMaterialIcon btnDelRow;
    private kevin.component.button.ButtonMaterialIcon btnDelRow1;
    private kevin.component.button.ButtonMaterialIcon btnDistribucion;
    private kevin.component.button.ButtonMaterialIcon btnDistribucion1;
    private kevin.component.button.ButtonMaterialIcon btnEditar;
    private kevin.component.button.ButtonMaterialIcon btnEditar1;
    private kevin.component.button.ButtonMaterialIcon btnExportExcel;
    private kevin.component.button.ButtonMaterialIcon btnExportExcel1;
    private kevin.component.button.ButtonMaterialIcon btnGuardar;
    private kevin.component.button.ButtonMaterialIcon btnGuardar1;
    private kevin.component.button.Button button1;
    private kevin.component.button.Button button2;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon1;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon2;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon3;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon4;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon5;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon6;
    private kevin.component.combobox.ComboBox cboEstado;
    private kevin.component.combobox.ComboBox cboEstado1;
    private kevin.component.combobox.ComboBox cboPlanilla;
    private kevin.component.combobox.ComboBox cboPlanilla1;
    private kevin.component.combobox.ComboBox cboUser;
    private kevin.component.combobox.ComboBox cboUser1;
    private kevin.component.checkbox.CheckBox chkCargaTrab;
    private kevin.component.checkbox.CheckBox chkCargaTrab1;
    private javax.swing.JCheckBoxMenuItem chkConfigSubTareos;
    private javax.swing.JCheckBoxMenuItem chkConfigSubTareos1;
    private kevin.component.date.MaterialDateChooser chooserFecha;
    private kevin.component.date.MaterialDateChooser chooserFecha1;
    private javax.swing.JPanel contenedor;
    private javax.swing.JPanel contenedor1;
    private kevin.component.edittext.EditText edtMaximo;
    private kevin.component.edittext.EditText edtMaximo1;
    private kevin.component.edittext.EditText edtMinimo;
    private kevin.component.edittext.EditText edtMinimo1;
    private kevin.component.edittext.EditText edtPromJornal;
    private kevin.component.edittext.EditText edtPromJornal1;
    private kevin.component.edittext.EditText edtPromRendim;
    private kevin.component.edittext.EditText edtPromRendim1;
    private kevin.component.edittext.EditText edtTotalPlantas;
    private kevin.component.edittext.EditText edtTotalPlantas1;
    private kevin.component.edittext.EditText edtTotalTrab;
    private kevin.component.edittext.EditText edtTotalTrab1;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta10;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private kevin.component.label.Etiqueta etiqueta4;
    private kevin.component.label.Etiqueta etiqueta5;
    private kevin.component.label.Etiqueta etiqueta6;
    private kevin.component.label.Etiqueta etiqueta7;
    private kevin.component.label.Etiqueta etiqueta8;
    private kevin.component.label.Etiqueta etiqueta9;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel paneDashboard;
    private javax.swing.JPanel paneDashboard1;
    private javax.swing.JPanel panelALL;
    private javax.swing.JPanel panelALL1;
    private kevin.component.panel.Panel panelCabecera;
    private kevin.component.panel.Panel panelCabecera1;
    private kevin.component.panel.Panel panelDistribucion;
    private kevin.component.panel.Panel panelDistribucion1;
    private javax.swing.JPanel panelEditarTareo;
    private javax.swing.JPanel panelEditarTareo1;
    private javax.swing.JPanel panelGeneralTareo;
    private javax.swing.JPanel panelGeneralTareo1;
    private kevin.component.panel.Panel panelSubTareos;
    private kevin.component.panel.Panel panelSubTareos1;
    private javax.swing.JScrollPane scrollDistirbucion;
    private javax.swing.JScrollPane scrollDistirbucion1;
    private javax.swing.JMenuItem sm_corregirdni;
    private kevin.component.tabla.Tabla tabla2;
    private kevin.component.tabla.Tabla tabla3;
    private kevin.component.tabla.TablaSmart tablaSubTareos;
    private kevin.component.tabla.TablaSmart tablaSubTareos1;
    // End of variables declaration//GEN-END:variables

    public class MyTableCellRender extends DefaultTableCellRenderer {

        private int patron;

        public MyTableCellRender(int pat) {
            this.patron = pat;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            switch (column) {

                default:
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    setIcon(null);
            }

//            if (table.getValueAt(row, column).toString().toLowerCase().contains("unknow")
//                    || table.getValueAt(row, column).toString().toLowerCase().contains("desconocido")
//                    || table.getValueAt(row, column).toString().toLowerCase().contains("no registrado")) {
//                setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/person_stop.png")));
//                setBackground(MaterialColor.RED_200);
//                setForeground(MaterialColor.BLACK);
//            }
            switch (table.getValueAt(row, patron).toString().toLowerCase()) {
                case "0":
                    this.setBackground(Color.WHITE);
                    this.setForeground(Color.black);
                    break;
                case "1":
                    this.setBackground(MaterialColor.AMBERA_200);
                    this.setForeground(Color.black);
                    break;
                case "2":
                    this.setBackground(MaterialColor.GREEN_200);
                    this.setForeground(Color.black);
                    break;
                case "unknow":
                case "desconocido":
                case "no registrado":
                    this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/person_stop.png")));
                    this.setBackground(MaterialColor.RED_200);
                    this.setForeground(MaterialColor.BLACK);
                    break;
            }

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return this;
        }
    }

    public class FormatDistribucion extends DefaultTableCellRenderer {

        public FormatDistribucion() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled());
            switch (column) {
                case 13:
                case 14:
                case 15:
                case 16:
                    setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    if (value.toString().equals("0.00")) {
                        setBackground(MaterialColor.RED_500);
                    } else {
                        setBackground(defaults.colorPrimaryDark);
                    }
                    setForeground(Color.WHITE);
                    break;
                case 17:
                    setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    if (value.toString().equals("0.00")) {
                        setBackground(MaterialColor.RED_500);
                    } else {
                        setBackground(Color.ORANGE);
                    }
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

    public class FormatSubTareo extends DefaultTableCellRenderer {

        public FormatSubTareo() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled());
            switch (column) {
                case 16:
                    if (value.toString().equals("CONSUMIDOR DADO DE BAJA")) {
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        setBackground(MaterialColor.RED_600);
                        setForeground(Color.WHITE);
                    }
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
}
