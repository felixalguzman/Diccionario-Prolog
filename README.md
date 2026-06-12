# Diccionario-Prolog

Diccionario de sinónimos (interfaz Swing en Java) que consulta una base de
hechos en **Prolog** a través de **JPL**, el puente nativo Java ↔ SWI-Prolog.

Escribe una palabra, pulsa **Buscar** y aparece un sinónimo. Haz clic sobre
cualquier palabra del resultado para reemplazarla por uno de sus sinónimos.

## Cómo funciona

- `src/prueba/prolog/DictionaryWindow.java` — ventana Swing. Lanza consultas
  Prolog `sacarsinonimo(Palabra, X)` con JPL.
- `Resources/Out/sinonimos.pl` — base de hechos `sinonimos(Palabra, Lista, Tipo)`
  más las reglas `sacarlista/2` y `sacarsinonimo/2`. Es el archivo que la
  aplicación consulta.

## Requisitos

1. **SWI-Prolog** instalado. Aporta el puente JPL: `libjpl.so`, `libswipl.so`
   y su propio `jpl.jar`.
   ```bash
   # Arch
   sudo pacman -S swi-prolog
   # Debian/Ubuntu
   sudo apt install swi-prolog
   ```
2. **JDK**. Usa una versión de Java **igual o mayor** a la que compiló el
   `jpl.jar` que trae tu SWI-Prolog (si `run.sh` falla con
   `UnsupportedClassVersionError`, exporta `JAVA_HOME` apuntando a un JDK más
   nuevo).

## Ejecutar

```bash
./run.sh
```

El script detecta automáticamente las rutas de SWI-Prolog
(`swipl --dump-runtime-variables`), compila y lanza la app con el classpath y
`java.library.path` correctos. **Ejecútalo desde la raíz del repo**: las rutas
a los archivos `.pl` son relativas al directorio de trabajo.

Para forzar un JDK concreto:
```bash
JAVA_HOME=/usr/lib/jvm/java-26-openjdk ./run.sh
```

## Notas

- El `jpl.jar` versionado en el repo es antiguo (2017) y su ABI puede no
  coincidir con el `libjpl.so` de tu SWI-Prolog. `run.sh` usa el `jpl.jar` que
  trae SWI-Prolog para evitar el desajuste.
- **Acentos:** los datos de origen (`Resources/data/th_es_ES_v2.dat` y por
  tanto `sinonimos.pl`) ya venían con los caracteres acentuados corruptos
  (`grafía` → `graf<U+FFFD>a`). Las palabras con acento no se encuentran. La
  corrupción es de origen en los datos, no recuperable desde este repo.
