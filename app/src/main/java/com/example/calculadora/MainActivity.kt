package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calculadora.databinding.ActivityMainBinding
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

/**
 * MainActivity
 * - Hereda de AppCompatActivity e implementa la interfaz View.OnClickListener.
 * -- AppCompatActivity es una clase base proporcionada por el framework de Android que se utiliza para crear actividades.
 * -- View.OnClickListener indica que esta clase manejará eventos de clic en vistas. Obliga a implementar el método OnClick
 */

/**
 * Funcion onCreate
 * - Es una función que inicia una actividad de android. Se llama cuando la aplicación se inicia por primera vez, o se reinicia después de detenerse o estar inactiva. Lo que hace es iniciar la aplicación, y cargar toda la configuración y diseño que tenga esta.
 * - Siempre tiene un override porque está sobreescribiendo un método de la clase padre "AppCompatActivity()"
 * - Recibe como parámetro un objeto de tipo Bundle, llamado savedInstanceState. Este objeto se usa para cuando la aplicación se reinicia. Contiene guardados todos los datos que la actividad había estado procesando antes de detenerse. Se utiliza para volver a iniciar la actividad en el mismo estado que el que tenía justo antes de detenerse, en vez de iniciarse desde 0.
 * - Esta función le pasa a la clase padre de nuestra actividad (MainActivity), el parámetro savedInstanceState, porque es la clase padre la que se encarga de procesar esa información que le llega, y cargar la actividad en el estado que estaba antes.
 */

/**
 * Biblioteca ViewBinding (variable binding)
 * ViewBinding es una característica del conjunto de herramientas de desarrollo de Android. Su propósito es permitir acceder a las vistas de los diseños XML desde el código de Kotlin o Java
 * - Para habilitarlo primero hay que configurarlo en el archivo "build.gradle" (explicado mejor ahí)
 * - Lo que hace es que va al XML, coge todo el diseño y crea una clase a la que llama con el mismo nombre que el XML (en este caso, como se llama activity_main, llamará a la clase ActivityMainBinding (añade Binding al final del nombre)
 * -- La clase ActivityMainBinding va a coger cada vista que haya en el XML (botones, EditTexts...), y los va a guardar dentro de variables, y cada una de esas variables va a ser una propiedad de esta clase. De esta forma, vamos a poder acceder a las vistas como si fueran propiedades de la clase. Accedemos a ellas igual que accedemos a las propiedades de cualquier clase (nombreClase.propiedad)
 * --- Ej. Aquí hemos llamado a la clase "binding", así que si quiero acceder a un botón sería "binding.myButton" (eso si, hay que poner my delante del nombre en inglés. Si queremos acceder a un EditText: "binding.myEditText"
 * -- Como cada vista está guardado dentro de una propiedad, podemos acceder tambien a las propiedades de cada vista (es como si hubieramos guardado un objeto como propiedad de una clase. Accedemos así : nombreClase.nombrePropiedad.nombrePropiedadDeLaPropiedad
 * --- Ej. Si queremos acceder al texto de un botón "binding.myButton.text". Podemos cambiar su valor: "binding.myButton.text= "Nuevo Texto""
 */


/**
 * Método setContentView (dentro de onCreate)
 * - Este método se encarga de cargar, inflar y mostrar el diseño del documento XML al usuario
 * - Aquí lo estamos llamando, no lo estamos creando, ojo! (a diferencia del método onCreate, que sí lo estamos creando)
 * - "Inflar" un diseño XML significa convertir el contenido y la estructura definidos en un archivo XML de diseño en una jerarquía de objetos de vista en memoria. Esta jerarquía de objetos de vista representa la interfaz de usuario definida en el archivo XML y se utiliza para mostrar la interfaz en la pantalla del dispositivo.
 * -- Cuando inflas un diseño XML, estás creando instancias de las vistas y widgets definidos en el archivo XML. Cada elemento del diseño (botones, cuadros de texto, imágenes, etc.) se convierte en un objeto de vista en memoria, y estos objetos se organizan de acuerdo con la estructura definida en el archivo XML.
 * -- Inflar un diseño XML generalmente se realiza utilizando métodos proporcionados por el sistema Android o por las bibliotecas de vinculación de vistas como ViewBinding o Data Binding.
 *
 * Forma básica de setContentView --> es "setContentView(R.layout.activity_main)"
 * - R es una clase autogenerada de android que se usa para hacer referencia a los recursos de la aplicación. Lo que hace android es coger la carpeta res y convertirla en la clase R, y convertir las subcarpetas en clases dentro de esa clase. De esta forma, es como si usáramos la ruta al fichero activity_main, pero en vez de poner un string, android lo ha convertido todo en clases
 *
 * setContentView en este archivo:
 *
 *       binding = ActivityMainBinding.inflate(layoutInflater);
 *       val view: View = binding.root
 *       setContentView(view)
 *
 *  Línea "binding = ActivityMainBinding.inflate(layoutInflater);"
 * - Primero coge la variable binding, que es una variable de tipo ActivityMainBinding que hemos creado
 * - Coge la clase ActivityMainBindig (que es el diseño XML convertido a una clase de kotlin, y cada propiedad es un elemento(vista) del XML)
 * - Accede al método .inflate de la clase ActivityMainBinding. Este método lo que hace es inflar la clase (convertir el XML en una jerarquía de objetos de vista en memoria)
 * -- Una "jerarquía de objetos de vista en memoria" es la estructura de objetos que se crea en la memoria RAM del dispositivo. Cuando inflas un diseño XML en Android, el sistema crea una jerarquía de objetos de vista en memoria que coincide con la estructura definida en el archivo XML. Esta jerarquía puede contener varios tipos de objetos de vista, como botones, cuadros de texto, imágenes, contenedores de diseño, etc.
 * - El método .inflate necesita un parámetro, que es layoutInflater. layoutInflater es un objeto (de la clase LayotutInflater), se encarga de decir cómo se tiene que inflar un diseño XML (o sea, dice, si tal cosa aparece en el XML, pues en kotlin creas esta otra cosa).
 * Entonces, en binding estamos guardando un objeto de la clase ActivityMainBinding que nos devuelve el usar el método .inflate()
 *
 * Líneas "val view: View = binding.root" y "setContentView(view)"
 * - El objeto (la instancia) de ActivityMainBinding tiene una propiedad llamada "root". Esta propiedad es la vista principal del diseño, y contiene todas las demás vistas definidas en el XML. La forma de representar las vistas en kotlin, es mediante la clase View, por lo tanto, devuelve un objeto de tipo View
 * - Finalmente, se usa setContentView para mostrar asignar una vista a esta actividad y mostrarla, y se le pasa por parámetro la vista que hemos creado a partir del binding(ActivityMainBinding), por lo tanto es esa la que va a usar.
 *
 *  */


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    val operadoresSinMenos = arrayOf("+", "x", "/", "^", "%", "V", "sen", "cos", "tan")
    val operadoresTipo1 = arrayOf("x", "/", "^")
    val operadoresTipo2 = arrayOf("+", "%")
    val operadoresTipo3 = arrayOf("V")
    val operadoresTipo4 = arrayOf("sen", "cos", "tan")
    val operadoresTipo5 = arrayOf("-")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        val view: View = binding.root
        setContentView(view)

        acciones()
    }


    /** ================================================================================== */
    /**   FUNCIONES PARA ESCUCHAR EVENTOS                                                  */
    /** ================================================================================== */

    /** FUNCION ACCIONES
     * - establece un escuchador de clic en el botón con el ID "button1, button2..." que se ha vinculado utilizando ViewBinding. Cuando se hace clic en este botón, se llamará a la función onClick de la actividad actual (en este caso, MainActivity) */
    private fun acciones() {
        //binding.button0.setOnClickListener(this@MainActivity)
        binding.button1.setOnClickListener(this@MainActivity)
        binding.button2.setOnClickListener(this@MainActivity)
        binding.button3.setOnClickListener(this@MainActivity)
        binding.button4.setOnClickListener(this@MainActivity)
        binding.button5.setOnClickListener(this@MainActivity)
        binding.button6.setOnClickListener(this@MainActivity)
        binding.button7.setOnClickListener(this@MainActivity)
        binding.button8.setOnClickListener(this@MainActivity)
        binding.button9.setOnClickListener(this@MainActivity)

        binding.buttonDecimal.setOnClickListener(this@MainActivity)
        binding.buttonSumar.setOnClickListener(this@MainActivity)
        binding.buttonRestar.setOnClickListener(this@MainActivity)
        binding.buttonMultiplicar.setOnClickListener(this@MainActivity)
        binding.buttonDividir.setOnClickListener(this@MainActivity)
        binding.buttonPorcentaje.setOnClickListener(this@MainActivity)

        binding.buttonPotencia?.setOnClickListener(this@MainActivity)
        binding.buttonRaizCuadrada?.setOnClickListener(this@MainActivity)
        binding.buttonSeno?.setOnClickListener(this@MainActivity)
        binding.buttonCoseno?.setOnClickListener(this@MainActivity)
        binding.buttonTangente?.setOnClickListener(this@MainActivity)

        binding.buttonBorrar.setOnClickListener(this@MainActivity)
        binding.buttonAC.setOnClickListener(this@MainActivity)

        binding.buttonIgual.setOnClickListener(this@MainActivity)
    }


    /** FUNCIÓN ONCLICK
     * - En la función onClick(p0: View?), se maneja la lógica de lo que sucede cuando se hace clic en un botón.
     * - El parámetro "v" es la vista (en este caso, el botón) que se ha hecho clic.
     */
    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.button0 -> binding.resultado.append("0");
            R.id.button1 -> binding.resultado.append("1");
            R.id.button2 -> binding.resultado.append("2");
            R.id.button3 -> binding.resultado.append("3");
            R.id.button4 -> binding.resultado.append("4");
            R.id.button5 -> binding.resultado.append("5");
            R.id.button6 -> binding.resultado.append("6");
            R.id.button7 -> binding.resultado.append("7");
            R.id.button8 -> binding.resultado.append("8");
            R.id.button9 -> binding.resultado.append("9");

            R.id.buttonDecimal -> escribirOperador(".");
            R.id.buttonSumar -> escribirOperador("+");
            R.id.buttonRestar -> escribirOperador("-");
            R.id.buttonMultiplicar -> escribirOperador("x");
            R.id.buttonDividir -> escribirOperador("/");
            R.id.buttonPorcentaje -> escribirOperador("%");
            R.id.buttonPotencia -> escribirOperador("^");
            R.id.buttonRaizCuadrada -> escribirOperador("V");
            R.id.buttonSeno -> escribirOperador("sen");
            R.id.buttonCoseno -> escribirOperador("cos");
            R.id.buttonTangente -> escribirOperador("tan");

            R.id.buttonAC -> accionBotonAC()
            R.id.buttonBorrar -> accionBotonBorrar()
            R.id.buttonIgual -> escribirResultado()
        }
    }


    /** ================================================================================== */
    /**   FUNCIONES PARA ESCRIBIR OPERADORES                                                */
    /** ================================================================================== */

    /** FUNCIÓN ESCRIBIROPERADOR
     * - Se ejecuta cuando se pulsa cualquier botón con un operador
     * - Filtra a los operadores según su tipo y los manda a una función distinta
     * - Las funciones a las que los envía, sirven para permitir escribir los operadores solo donde suele dejar una calculadora.    También sirven para sustituir unos operadores por otros*/
    fun escribirOperador(operador: String) {
        var operacion = binding.resultado.text.toString();
        if (operadoresTipo1.contains(operador) || operadoresTipo2.contains(operador)) escribirOperadorTipos1y2(
            operacion,
            operador
        )
        else if (operadoresTipo3.contains(operador)) escribirOperadorTipo3(operacion, operador)
        else if (operadoresTipo4.contains(operador)) escribirOperadorTipo4(operacion, operador)
        else if (operadoresTipo5.contains(operador)) escribirOperadorTipo5(operacion, operador)
        else if (operador == ".") escribirPuntoDecimal(operacion, ".")
    }


    /**FUNCIONES ESCRIBIROPERADORTIPO(S)X
     * - Crean unos flags para determinar si la operación tiene las características que debe tener para que se pueda escribir un operador o no, o si ese operador puede sustituir a otro que ya está escrito
     */
    // tipo 1 -> x, /, ^
    // tipo 2 -> +, %
    fun escribirOperadorTipos1y2(operacion: String, nuevoOperador: String) {
        println("entra a escribirOperadorTipos1y2")
        var hayOperador = false;
        var primerCaracterEsOperador = false
        var ultimoCaracterEsNumero = false

        if (contieneOperador(operacion)) hayOperador = true;
        if (operadorAlPrincipio(operacion)) primerCaracterEsOperador = true;
        if (operacion.isNotEmpty() && operacion[operacion.length - 1].isDigit()) {
            ultimoCaracterEsNumero = true
        }

        if ((!hayOperador) && (ultimoCaracterEsNumero)) binding.resultado.append(nuevoOperador)
        else if (hayOperador && (!primerCaracterEsOperador)) {
            var nuevaOperacion = reemplazarOperador(operacion, nuevoOperador)
            binding.resultado.setText(nuevaOperacion)
        }
    }

    // tipo 3 -> V
    fun escribirOperadorTipo3(operacion: String, nuevoOperador: String) {
        println("entra a escribirOperadorTipo3")
        var noHayCaracteres = false
        var hayOperador = false
        var ultimoCaracterEsNumero = false

        if (operacion.length == 0) noHayCaracteres = true;
        if (contieneOperador(operacion)) hayOperador = true;
        if (operacion.isNotEmpty() && operacion[operacion.length - 1].isDigit()) {
            ultimoCaracterEsNumero = true
        }

        if (noHayCaracteres || ((!hayOperador) && (ultimoCaracterEsNumero))) binding.resultado.append(
            nuevoOperador
        )
        else if (hayOperador) {
            var nuevaOperacion = reemplazarOperador(operacion, nuevoOperador)
            binding.resultado.setText(nuevaOperacion)
        }
    }

    // tipo 4 -> sen, cos, tan
    fun escribirOperadorTipo4(operacion: String, nuevoOperador: String) {
        println("entra a escribirOperadorTipo4")
        var noHayCaracteres = false
        var hayOperador = false;
        var primerCaracterEsOperador = false

        if (operacion.length == 0) noHayCaracteres = true;
        if (contieneOperador(operacion)) hayOperador = true;
        if (operadorAlPrincipio(operacion)) primerCaracterEsOperador = true;

        if (noHayCaracteres) binding.resultado.append(nuevoOperador)
        else if (primerCaracterEsOperador && hayOperador) {
            var nuevaOperacion = reemplazarOperador(operacion, nuevoOperador)
            binding.resultado.setText(nuevaOperacion)
        }
    }

    //tipo 5 -> -
    fun escribirOperadorTipo5(operacion: String, nuevoOperador: String) {
        println("entra a escribirOperadorTipo5")
        var noHayCaracteres = false
        var hayOperador = false;
        var primerCaracterEsOperador = false
        var ultimoCaracterEsNumero = false
        var ultimoCaracterEsOperador = false
        var numeroDeSimbolosMenos = 0;

        if (operacion.length == 0) noHayCaracteres = true;
        if (contieneOperador(operacion)) hayOperador = true;
        if (operadorAlPrincipio(operacion)) primerCaracterEsOperador = true;
        if (operadorAlFinal(operacion)) ultimoCaracterEsOperador = true;
        if (operacion.isNotEmpty() && operacion[operacion.length - 1].isDigit()) {
            ultimoCaracterEsNumero = true
        }
        numeroDeSimbolosMenos = contarSimbolosMenos()

        if (noHayCaracteres ||
            (ultimoCaracterEsOperador && numeroDeSimbolosMenos < 2) ||
            (ultimoCaracterEsNumero && numeroDeSimbolosMenos < 2 && !hayOperador)) {
            binding.resultado.append(nuevoOperador)
        }
        else if (hayOperador && (!primerCaracterEsOperador)) {
            var nuevaOperacion = reemplazarOperador(operacion, nuevoOperador)
            binding.resultado.setText(nuevaOperacion)
        }
    }

    fun escribirPuntoDecimal(operacion: String, nuevoOperador: String) {
        var ultimoCaracterEsNumero = false
        if (operacion[operacion.length - 1].isDigit()) ultimoCaracterEsNumero = true
        if (ultimoCaracterEsNumero) binding.resultado.append(nuevoOperador)
    }


    /** ================================================================================== */
    /**   FUNCIONES PARA ESCRIBIR EL RESULTADO                                             */
    /** ================================================================================== */

    /** FUNCIÓN ESCRIBIRRESULTADO
     * - Se activa al pulsar el botón "="
     * - Coge la operación de la caja de texto y llama a la función buscarOperadorConMenos para averiguar cual es el operador de la operación
     * - A partir de ahí filtra las operaciones dependiendo de si el número va delante o detrás del operador, o si son operaciones de dos números
     * - Cuando realiza la operación llama a la función formatoResultado para formatear el resultado antes de escribirlo
     */
    fun escribirResultado() {
        var operacion = binding.resultado.text.toString()
        var operador = buscarOperador(operacion)
        var operador1 = 0.0;
        var operador2 = 0.0;

        //Todas las operaciones cuyo operador no sea -
        if (operacion != null && operador !="" && operador != "-") {
            var operacionDividida = operacion.split(operador);

            when {
                //Operaciones de miembro + operador
                // - Chequea que el segundo miembro sea un string vacío (.split devuelve un string vacío cuando no hay nada en ese lado del caracter por el que corta)
                operacionDividida[1] == "" -> {
                    operador1 = operacionDividida[0].toDouble()
                    when (operador) {
                        "%" -> formatearResultado(operador1 / 100)
                        "+" -> formatearResultado(operador1 + operador1)
                        "x" -> formatearResultado(operador1 * operador1)
                    }
                }
                //Operaciones de operador + miembro
                operacionDividida[0] == "" -> {
                    operador2 = operacionDividida[1].toDouble()
                    when (operador) {
                        "V" -> formatearResultado(sqrt(operador2))
                        "sen" -> formatearResultado(sin(operador2))
                        "cos" -> formatearResultado(cos(operador2))
                        "tan" -> formatearResultado(tan(operador2))
                    }
                }
                //Operaciones de 2 miembros
                operacionDividida[0] != "" && operacionDividida[1] != "" -> {
                    operador1 = operacionDividida[0].toDouble()
                    operador2 = operacionDividida[1].toDouble()
                    when (operador) {
                        "+" -> formatearResultado(operador1 + operador2)
                        "x" -> formatearResultado(operador1 * operador2)
                        "/" -> formatearResultado(operador1 / operador2)
                        "%" -> formatearResultado((operador2 / 100) * operador1)
                        "^" -> formatearResultado(operador1.pow(operador2))
                        "V" -> formatearResultado(operador2.pow(1.0 / operador1))
                    }
                }
            }
        }

        //Todas las operaciones cuyo operador sea -
        else if(operacion != null && operador !="" && operador == "-") {
            var operacionTemporal = reemplazarOperador(operacion, "A")

            var operacionDividida = operacionTemporal.split("A")
            println(operacionDividida[0])
            println(operacionDividida[1])

            when {
                operacionDividida[0] == "" -> {
                    operador1 = operacionDividida[0].toDouble()
                    formatearResultado(operador1 - operador1)
                }

                operacionDividida[0] != "" && operacionDividida[1] != "" -> {
                    operador1 = operacionDividida[0].toDouble()
                    operador2 = operacionDividida[1].toDouble()
                    formatearResultado(operador1 - operador2)
                }
            }
        }
        binding.historial.setText(operacion);
    }


    /** FUNCION FORMATEARRESULTADO
     * - Convierte el resultado a int y luego a string si no es decimal
     * - Lo convierte directamente a string si es decimal
     */
    fun formatearResultado(numero: Double) {
        if (numero % 1 == 0.0) {
            binding.resultado.setText(numero.toInt().toString())

        } else {
            binding.resultado.setText(numero.toString())
        }
    }


    /** ================================================================================== */
    /**   FUNCIONES PARA BORRAR                                                            */
    /** ================================================================================== */
    fun accionBotonAC() {
        binding.resultado.setText("")
        binding.historial.setText("")
    }


    fun accionBotonBorrar() {
        var texto = binding.resultado.text.toString()
        if (texto.isNotEmpty()) {
            binding.resultado.setText(texto.substring(0, texto.length - 1))
        }
    }


    /** ================================================================================== */
    /**   FUNCIONES DE UTILIDAD                                                            */
    /** ================================================================================== */


    /** FUNCION BUSCAROPERADOR
     * - Coge por parámetro el string que es la operación
     * - Primero recorre el array operadoresSinMenos (donde están todos los operadores excepto "-")
     * - Luego, recorre la operación para ver si hay algún símbolo "-" que esté entre dos dígitos, lo que significa que es un operador de resta, y no un "-" que convierte un número en negativo. O sea, que es un operador.
     * - Si encuentra un operador lo devuelve. Si no encuentra nada devuelve un string vacío
     */
    fun buscarOperador(string: String): String {
        System.out.println("entra a buscarOperador")
        for (operador in operadoresSinMenos) {
            if (string.contains(operador)) {
                System.out.println("hay un operador $operador ")
                return operador;
            }
        }

        for (i in 0 until string.length) {
            if (string[i] == '-') {
                if((i-1) in string.indices && (i+1) in string.indices) {
                    if (string[i-1].isDigit() && string[i+1].isDigit()) {
                        System.out.println("hay un operador -")
                        return "-";
                    }
                }
            }
        }
        System.out.println("No hay un operador")
        return "";
    }


    /** FUNCION CONTIENEOPERADOR
     * - Pasa la operación a buscarOperador, que le devuelve el operador si existe
     * - Si hay un operador, devuelve true, si no lo hay, false.
     */
    fun contieneOperador(string: String): Boolean {
        System.out.println("entra a contieneOperador")
        if (buscarOperador(string) != "") {
            System.out.println("contiene un operador")
            return true
        }
        else {
            System.out.println("no contiene un operador")
            return false
        }
    }


    /** FUNCION REEMPLAZAROPERADOR
     * - Pasa la operación a buscarOperador, que le devuelve el operador si existe
     * - Si hay un operador, lo reemplaza de la operación. Finalmente devuelve la operación modificada
     */
    fun reemplazarOperador(string: String, nuevoOperador: String): String {
        System.out.println("entra a reemplazarOperador")
        var nuevoString = "";
        var operadorEnOperacion = buscarOperador(string)

        if((operadorEnOperacion != "") && (operadorEnOperacion!= "-")) {
            nuevoString = string.replace(operadorEnOperacion, nuevoOperador);
            System.out.println("operador $operadorEnOperacion reemplazado por $nuevoOperador")
        }
        else if(operadorEnOperacion == "-"){
            for (i in 0 until string.length) {
                if (string[i] == '-') {
                    if((i-1) in string.indices && (i+1) in string.indices) {
                        if (string[i-1].isDigit() && string[i+1].isDigit()) {
                            nuevoString = string.substring(0, i) + nuevoOperador + string.substring(i+1)
                            System.out.println("operador $operadorEnOperacion reemplazado por $nuevoOperador -> $nuevoString")
                        }
                    }
                }
            }
        }
        else {
            System.out.println("No se ha reemplazado el operador")
            nuevoString = string
        }
        return nuevoString
    }


    fun contarSimbolosMenos(): Int {
        System.out.println("entra a contarSimbolosMenos")
        var operacion = binding.resultado.text
        var contador = 0;
        for (caracter in operacion) {
            if (caracter == '-') {
                contador++;
            }
        }
        System.out.println("Hay $contador simbolos menos")
        return contador;
    }


    fun operadorAlPrincipio(string: String): Boolean {
        System.out.println("entra a operadorAlPrincipio")
        for (operador in operadoresSinMenos) {
            if (string.length >= 1 && string.substring(0, 1) == operador) {
                System.out.println("hay un operador $operador")
                return true
            } else if (string.length >= 3 && string.substring(0, 3) == operador) {
                System.out.println("hay un operador $operador")
                return true
            }
        }
        System.out.println("No hay un operador")
        return false
    }


    fun operadorAlFinal(string: String): Boolean {
        System.out.println("entra a operadorAlFinal")
        for (operador in operadoresSinMenos) {
            if (string.length >= 1 && string.substring(string.length - 1) == operador) {
                System.out.println("hay un operador $operador")
                return true
            } else if (string.length >= 3 && string.substring(string.length - 3) == operador) {
                System.out.println("hay un operador $operador")
                return true
            }
        }
        System.out.println("No hay un operador")
        return false
    }
}
