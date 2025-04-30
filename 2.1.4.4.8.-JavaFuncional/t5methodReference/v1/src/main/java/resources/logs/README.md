# LOGS

Cambios en Console para poder ejecutar desde el repositorio varios proyectos y diferenciar los logs.

Añadir un método para obtener el nombre de la la clase que tiene main:

private static String getMainClassName() {
	for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
		if ("main".equals(element.getMethodName())) {
			return element.getClassName();
		}
	}
	return "unknown";

private static final String mainClassName = getMainClassName();
private static final String mainClassSimpleName = mainClassName.substring(mainClassName.lastIndexOf('.') + 1);
private static final String logFolderName = mainClassSimpleName;

private static final String BASE_PATH = System.getProperty("user.dir");
private static final String HEAD_PATH = BASE_PATH + "/resources/logs/" + logFolderName + "/" ;

Crear carpeta si no existe:

En try catch meter 
File logDir = new File(HEAD_PATH);
if (!logDir.exists()) {
	logDir.mkdirs();
}