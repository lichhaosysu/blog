drop table if exists category;
drop table if exists article;
drop table if exists comment;
drop table if exists tag;
drop table if exists tag_article;
drop table if exists user;

--
-- 表的结构 'category'
--

CREATE TABLE category(
	cat_id int not null AUTO_INCREMENT comment '类别标识',
	cat_name varchar(250) comment '类别名称',
	create_date datetime comment '创建时间',
	constraint pk_category PRIMARY KEY (cat_id)
);

COMMENT ON TABLE category is '文章分类表';

--
-- 表的结构 'article'
--
CREATE TABLE article (
	article_id int not null AUTO_INCREMENT comment '文章标识',
	title varchar(250) comment '文章标题',
	content longtext comment '文章内容',
	summary text comment '文章摘要',
	visit_count int(11) DEFAULT 0 comment '浏览次数',
	is_published tinyint(1) DEFAULT 1 comment '是否已经发布，已保存但未发布的文章进入草稿箱',
	type varchar(20) DEFAULT 'post' comment '文章类型，默认为post文章类型；page类型为页面公告',
	link varchar(250) comment '固定链接，用于发布在外网时使用',
	comment_status varchar(20) DEFAULT 'open' comment '是否允许评论，open：允许评论；close：关闭评论',
	article_status varchar(20) DEFAULT 'public' comment '文章状态，public：公开；private：不公开',
	password varchar(20) comment '当文章状态为private时，非管理员访问需要输入密码',
	create_date datetime comment '创建时间',
	modify_date datetime comment '最后修改时间',
	article_cat_id int comment '所属类别的类别标识',
	constraint pk_article PRIMARY KEY (article_id),
	constraint fk_article_category FOREIGN KEY (article_cat_id) references category(cat_id)
);

COMMENT ON TABLE article is '文章表';

--
-- 表的结构 'comment'
--

CREATE TABLE comment (
	comment_id int not null AUTO_INCREMENT comment '评论标识',
	comment_content mediumtext comment '评论内容',
	comment_article_id varchar(32) comment '该评论对应的文章标识',
	comment_name varchar(255) comment '评论者姓名',
	comment_email varchar(255) comment '评论者的邮箱',
	comment_url varchar(255) comment '评论者友情链接',
	comment_parent_id varchar(32) comment '用于查询所归属的评论',
	create_date datetime comment '评论时间',
	constraint pk_comment PRIMARY KEY (comment_id),
	constraint fk_comment_article FOREIGN KEY (comment_article_id) references article(article_id)
);

COMMENT ON TABLE comment is '评论表';



--
-- 表的结构 'tag'
--

CREATE TABLE tag (
 tag_id int not null AUTO_INCREMENT comment '标签标识',
 tag_name varchar(250) comment '标签名称',
 create_date datetime comment '创建时间',
 constraint pk_tag PRIMARY KEY (tag_id)
);

COMMENT ON TABLE tag is '标签表';

--
-- 表的结构 'tag_article'
--

CREATE TABLE tag_article (
 ta_tag_id int comment '标签标识',
 ta_article_id varchar(32) comment '文章标识',
 constraint fk_tag FOREIGN KEY (ta_tag_id) references tag(tag_id),
 constraint fk_article FOREIGN KEY (ta_article_id) references article(article_id)
);

COMMENT ON TABLE tag_article is '标签文章关系表';

--
-- 表的结构 'user'
--

CREATE TABLE user (
 user_id int not null AUTO_INCREMENT comment '用户标识',
 user_name varchar(250) comment '用户名称',
 user_password varchar(250) comment '用户密码',
 create_date datetime comment '创建时间',
 constraint pk_user PRIMARY KEY (user_id)
);

COMMENT ON TABLE user is '用户表';
