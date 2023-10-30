# PruebaEurekaQA

El presente repositorio contiene los ejercicios solicitados para la prueba QA Automatizador - Eureka.

## Ejercicio 1

Test automático sobre el sitio web del Banco Central.

### Stack Tecnológico

- IDE Eclipse.
- Java JDK
- Maven
- Selenium
- Cucumber

### Descripción aplicativo

 

1.

2.

3.

4.

5.

### Ejecución aplicativo

Para la realización del aplicativo se utilizó Maven como gestor de dependencias, por tanto no se debe instalar ninguna de manera manual.

Para ejecutar el aplicativo se debe ejecutar (valga la redundancia) como un Java Test:


## Ejercicio 2

Pruebas automatizadas sobre APIs.

Dentro del repositorio existe un archivo "API CMF" con extensión Json, el cual corresponde a la colección creada en Postman. Dentro de la colección existen 2 requests que se explican a continuación.

### Solicitud API KEY

Para realizar las pruebas sobre la API se necesita una API Key, la cual es solicitada en el siguiente formulario: https://api.cmfchile.cl/api_cmf/contactanos.jsp

En mi caso, obtuve la siguiente API Key: e6c287e75a6d492005437a544a8ad8cedf2fe89e

### Creación variables de colección

- URL base: https://api.cmfchile.cl/api-sbifv3/recursos_api

![URL base](images/URL.png)

- API Key: La API Key la cree en la sección *Authorization* de cada request:

![URL base](images/ApiKey.png)

### Request 1 "Dólar año anterior"

Petición GET a la URL: {{URL}}/dolar/2022?formato=json

**Validaciones:**

Para realizar las validaciones se debe ir sección *Test* y escribir el código en el lenguaje JavaScript.

Variables a utilizar:
```JS
//Respuesta JSON
var jsonData = pm.response.json();
//Tiempo de respuesta en milisegundos
var responseTime = pm.response.responseTime;
//Variable para la fecha buscada
var fechaBuscada = "2022-08-31";
```

1. Validar que la respuesta se procese por debajo de 500 milisegundos:

```JS
//Verificar si "Dolares" está presente en la respuesta:
pm.test("Verificar que la respuesta contiene 'Dolares'", function () {
    pm.expect(jsonData).to.have.property("Dolares");
});
```

2. Validar que la respuesta contenga un texto que diga “Dolares”:

```JS
//Verificar que el tiempo de respuesta sea menor que 500 ms:
pm.test("Tiempo de respuesta por debajo de 500 ms", function () {
    pm.expect(responseTime).to.be.below(500);
});
```

3. Validar que la respuesta contenga un dato para el 31 de agosto de 2022:

```JS
//Verificar si la respuesta contiene un dato para una fecha específica:
pm.test("Verificar que la respuesta contiene datos para el 31 de agosto de 2022", function () {
    var elementoEncontrado = jsonData.Dolares.find(function (elemento) {
        return elemento.Fecha === fechaBuscada;
    });
    pm.expect(elementoEncontrado).to.not.be.undefined;
});
```

**Resultados:**

- Petición GET: Al ejecutar la petición se obtiene una respuesta correcta, la cual se puede corroborar con un *status code 200*, presentando el siguiente body: 

![URL base](images/request1.png)

- Validaciones: Se puede verificar que los test han pasado en la pestaña de *Test Results*:

![URL base](images/test1.png)

### Request 2 "Euro día actual"

Petición GET a la URL: {{URL}}/euro?formato=json

**Validaciones:**

Para realizar las validaciones se debe ir sección *Test* y escribir el código en el lenguaje JavaScript.

Variables a utilizar:
```JS
//Respuesta JSON
var jsonData = pm.response.json();
```

1. Validar que la respuesta contenta un encabezado "Content-Type" en el header:

```JS
// Verificar que el encabezado "Content-Type" esté presente en la respuesta:
pm.test("Verificar que el encabezado 'Content-Type' esté presente", function () {
    pm.response.to.have.header("Content-Type");
});
```

2. Validar que la respuesta contenga un texto que diga “Euros”:

```JS
//Verificar que la respuesta contiene la palabra "Euros":
pm.test("Verificar que la respuesta contiene 'Euros'", function () {
    pm.expect(jsonData).to.have.property("Euros");
});
```

3. Validar que la respuesta no contine valores nulos en los campos Valor y Fecha:

```JS
//Verificar que los campos Valor y Fecha no sean nulos:
pm.test("Validar que los campos 'Valor' y 'Fecha' no sean nulos", function() {
    jsonData.Euros.find(function (elemento) {
        return (pm.expect(elemento.Valor).not.to.be.null && pm.expect(elemento.Fecha).not.to.be.null);
    });
});
```

**Resultados:**

- Petición GET: Al ejecutar la petición se obtiene una respuesta correcta, la cual se puede corroborar con un *status code 200*, presentando el siguiente body: 

![URL base](images/request2.png)

- Validaciones: Se puede verificar que los test han pasado en la pestaña de *Test Results*:

![URL base](images/test2.png)

### CONSIDERACIÓN

Al importar el archivo Json existe la posibilidad que no se cree la variable de colección URL utilizada para almacenar la URL base de la API. En dicho caso, se debe crear esta variable de colección de la siguiente manera:

