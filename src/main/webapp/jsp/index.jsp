<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/includes/common.inc"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8" />
		<%@ include file="/jsp/includes/bootstrap-css.inc"%>
		<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
		<link href="<%=contextPath%>/img/favicon.png" rel="shortcut icon" />
		<title>Let's Blog!</title>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> </a>
					<a class="brand" href="#">Project name</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active">
								<a href="#">Home</a>
							</li>
							<li>
								<a href="#about">About</a>
							</li>
							<li>
								<a href="#contact">Contact</a>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown
									<b class="caret"></b> </a>
								<ul class="dropdown-menu">
									<li>
										<a href="#">Action</a>
									</li>
									<li>
										<a href="#">Another action</a>
									</li>
									<li>
										<a href="#">Something else here</a>
									</li>
									<li class="divider"></li>
									<li class="nav-header">
										Nav header
									</li>
									<li>
										<a href="#">Separated link</a>
									</li>
									<li>
										<a href="#">One more separated link</a>
									</li>
								</ul>
							</li>
						</ul>
						<form class="navbar-form pull-right">
							<input class="span2" type="text" placeholder="Email">
							<input class="span2" type="password" placeholder="Password"
								name="default_fieldname">
							<button type="submit" class="btn">
								Sign in
							</button>
						</form>
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>

		<div class="container">

			<!-- Main hero unit for a primary marketing message or call to action -->
			<div class="hero-unit">
				<h1>
					Hello, world!
				</h1>
				<p>
					This is a template for a simple marketing or informational website.
					It includes a large callout called the hero unit and three
					supporting pieces of content. Use it as a starting point to create
					something more unique.
				</p>
				<p>
					<a class="btn btn-primary btn-large">Learn more »</a>
				</p>
			</div>

			<!-- Example row of columns -->
			<div class="row">
				<div class="span4">
					<h2>
						Heading
					</h2>
					<p>
						Donec id elit non mi porta gravida at eget metus. Fusce dapibus,
						tellus ac cursus commodo, tortor mauris condimentum nibh, ut
						fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.
					</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<div class="span4">
					<h2>
						Heading
					</h2>
					<p>
						Donec id elit non mi porta gravida at eget metus. Fusce dapibus,
						tellus ac cursus commodo, tortor mauris condimentum nibh, ut
						fermentum massa justo sit amet risus. Etiam porta sem malesuada
						magna mollis euismod. Donec sed odio dui.
					</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
				<div class="span4">
					<h2>
						Heading
					</h2>
					<p>
						Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
						egestas eget quam. Vestibulum id ligula porta felis euismod
						semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris
						condimentum nibh, ut fermentum massa justo sit amet risus.
					</p>
					<p>
						<a class="btn" href="#">View details »</a>
					</p>
				</div>
			</div>

			<div class="container">

				<form class="bs-docs-example form-horizontal">
					<legend>
						Legend
					</legend>
					<div class="control-group">
						<label class="control-label" for="inputEmail">
							Email
						</label>
						<div class="controls">
							<input type="text" id="inputEmail" placeholder="Email">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">
							Password
						</label>
						<div class="controls">
							<input type="password" id="inputPassword" placeholder="Password"
								name="default_fieldname">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox">
								<input type="checkbox">
								Remember me
							</label>
							<button type="submit" class="btn">
								Sign in
							</button>
						</div>
					</div>
				</form>
				
				<div class="mini-layout fluid">
            <div class="mini-layout-sidebar"></div>
            <div class="mini-layout-body"></div>
          </div>
				

			</div>

			<hr>

			<footer>
				<p>
					© Company 2012
				</p>
			</footer>

		</div>
		
		<%@ include file="/jsp/includes/bootstrap-js.inc"%>
	</body>
</html>