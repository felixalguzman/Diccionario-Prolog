#!/usr/bin/env bash
# Compila y ejecuta el Diccionario de Sinonimos.
#
# Requisitos:
#   - SWI-Prolog instalado (provee el puente JPL nativo: libjpl.so + jpl.jar)
#   - Un JDK cuya version sea >= a la usada para compilar el jpl.jar de SWI-Prolog
#
# JPL es un puente nativo Java<->SWI-Prolog, por eso hay que apuntar la JVM a
# las librerias de SWI (LD_LIBRARY_PATH / java.library.path) y usar el jpl.jar
# que trae SWI-Prolog (coincide con la version del libjpl.so instalado).
set -euo pipefail
cd "$(dirname "$0")"

command -v swipl >/dev/null || { echo "ERROR: SWI-Prolog (swipl) no esta instalado."; exit 1; }

# Detectar rutas de SWI-Prolog automaticamente.
eval "$(swipl --dump-runtime-variables)"   # define PLBASE, PLLIBDIR, ...
SWI_LIB_DIR="$PLLIBDIR"                     # contiene libswipl.so y libjpl.so
JPL_JAR="$PLBASE/lib/jpl.jar"              # jpl.jar que coincide con el nativo

[ -f "$JPL_JAR" ] || { echo "ERROR: no se encontro $JPL_JAR"; exit 1; }

JAVAC="${JAVA_HOME:+$JAVA_HOME/bin/}javac"
JAVA="${JAVA_HOME:+$JAVA_HOME/bin/}java"

echo "Compilando..."
rm -rf build/classes && mkdir -p build/classes
"$JAVAC" -cp "$JPL_JAR" -encoding UTF-8 -d build/classes src/prueba/prolog/*.java

echo "Ejecutando..."
export LD_LIBRARY_PATH="$SWI_LIB_DIR:${LD_LIBRARY_PATH:-}"
exec "$JAVA" -cp "build/classes:$JPL_JAR" \
  -Djava.library.path="$SWI_LIB_DIR" \
  --enable-native-access=ALL-UNNAMED \
  prueba.prolog.PruebaProlog
