<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>51找你</title>

<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/script.js"></script>
</head>
<body>

	<div class="nav-bg">
		<div class="nav">
			<span><a href="#"><img src="<%=path%>/images/logo.png"
					height="100%" /> </a></span>
		</div>
		<div class="navfil"></div>
	</div>

	<div class="focus-bg">
		<div class="focus">
			<div id="carousel_container" class="con">
				<div class="pt pt_cur" style="display: block">
					<img src="<%=path%>/images/0.png" alt="" />
				</div>
				<div class="pt">
					<img src="<%=path%>/images/1.jpg" alt="" />
				</div>
				<div class="pt">
					<img src="<%=path%>/images/2.png" alt="" />
				</div>
			</div>

			<div class="znpack">
				<div>
					<img src="<%=path%>/images/51zhaoni_qr.png" class="download_qr" />
				</div>

				<br /> <a href="<%=path%>/download/51zhaoni.apk" class="download_android">
					<div class="d_left"></div>
					<div class="d_right">
						<span>立即下载</span> V1.0.0&nbsp;2.5M&nbsp;2014/09/23
					</div>
				</a>
			</div>

			<div class="autoS">
				<ul id="carousel_point">
					<li turn="1" class="libg"></li>
					<li turn="2"></li>
					<li turn="3"></li>
				</ul>
			</div>

		</div>
	</div>

	<!--S footer-->
	<div class="foot">
		<p>
			Copyright &copy; <span id="copytime">2014</span> 51zhaoni
		</p>
		<p>51找你 版权所有</p>
		<div class="footfil"></div>
	</div>
	<!--E footer-->

	<script type="text/javascript">
		var carousel_taskId;
		(function() {
			bindCarousel();
			carousel_taskId = setTimeout(carouselNext, 4000);
		})(jQuery)
	</script>
</body>
</html>
