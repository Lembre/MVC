/**
 * 调用后台批量删除方法
 * 这是jQuery写法，代替了document.getElementById,变成jQuery对象，它有他的方法
 */
function deleteBatch(basePath) {/*外部传入一个bathPath参数过来*/
	$("#mainForm").attr("action",basePath + "DeleteBatchServlet.action");
	$("#mainForm").submit();
}