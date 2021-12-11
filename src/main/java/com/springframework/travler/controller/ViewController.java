package com.springframework.travler.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class ViewController {

//	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
//	private static final List<String> staticResourcesPath = new ArrayList<String>(Arrays.asList("images", "js", "css"));

//	@RequestMapping({"/view/{moduleName}/{viewName}"})
//	public void getView(HttpServletRequest request, HttpServletResponse response,
//			@PathVariable(required = false) String moduleName, @PathVariable String viewName) throws Exception {
//		BufferedOutputStream bos = null;
//		String viewFilePath = null;
//		String mimeType = null;
//
//		try {
//			String cometViewPath = null;
//			if (!staticResourcesPath.stream().anyMatch((s) -> {
//				return s.equals(moduleName);
//			})) {			 
//				cometViewPath = System.getProperty("application.html.dir");
//				if (moduleName != null) {
//					viewFilePath = Paths.get(cometViewPath, moduleName, String.format("%s%s", viewName, ".html"))
//							.toString();
//				}
//				mimeType = "text/html;charset=utf-8";
//			} else if (moduleName.equals("images")) {
//				cometViewPath = System.getProperty("application.images.dir");
////				mimeType = FileUtil.getImageMimeTypeExtensionName(request.getRequestURI());
//				viewName = request.getRequestURI().replace(String.format("/view/%s", moduleName), "").trim();
//				viewFilePath = Paths.get(cometViewPath, viewName).toString();
//			} else if (moduleName.equals("js")) {
//				cometViewPath = System.getProperty("application.js.dir");
//				mimeType = "application/javascript;charset=utf-8";
//				viewName = request.getRequestURI().replace(String.format("/view/%s", moduleName), "").trim();
//				viewFilePath = Paths.get(cometViewPath, viewName).toString();
//			} else if (moduleName.equals("css")) {
//				cometViewPath = System.getProperty("application.css.dir");
//				mimeType = "text/css;charset=utf-8";
//				viewName = request.getRequestURI().replace(String.format("/view/%s", moduleName), "").trim();
//				viewFilePath = Paths.get(cometViewPath, viewName).toString();
//			} 
//
//			if (cometViewPath == null) {
//				throw new IOException("파일을 찾을 수 없습니다." + cometViewPath);
//			}
//
//			logger.info("View File Path -> {}", viewFilePath);
//			File directory = new File("C:\\Douzone\\dews-web\\repository\\view\\html\\index.html");
//			if (directory.exists()) {
//				FileInputStream fs = new FileInputStream(directory);
//				byte[] readByte = ByteStreams.toByteArray(fs);
//				
//				response.setContentType(mimeType);
//				response.setHeader("Content-Length", "" + readByte.length);
//				bos = new BufferedOutputStream(response.getOutputStream());
//				bos.write(readByte);
//				bos.flush();
//			}
//		} catch (IOException var14) {
//			logger.error(var14.getMessage(), var14);
//			response.sendError(404);
//		} catch (Exception var15) {
//			logger.error(var15.getMessage(), var15);
//		} finally {
//			if (bos != null) {
//				bos.close();
//			}
//
//		}
//	}
}
