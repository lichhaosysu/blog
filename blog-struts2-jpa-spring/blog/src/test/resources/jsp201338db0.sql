# MySQL-Front 5.1  (Build 3.57)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: 175.41.24.50    Database: jsp201338db0
# ------------------------------------------------------
# Server version 5.1.61-ius

#
# Source for table article
#
drop database if exists blog;
create database blog default charset utf8;
use blog;

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` varchar(32) NOT NULL DEFAULT '' COMMENT '文章标识',
  `title` varchar(250) DEFAULT NULL COMMENT '文章标题',
  `content` longtext COMMENT '文章内容',
  `visit_count` int(11) DEFAULT NULL COMMENT '浏览次数',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `modify_date` datetime DEFAULT NULL COMMENT '最后修改日期',
  `is_published` tinyint(1) DEFAULT NULL COMMENT '是否已经发布，已保存但未发布的文章进入草稿箱',
  `summary` text COMMENT '文章摘要',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

#
# Dumping data for table article
#

INSERT INTO `article` VALUES ('2fa998b23b28f1b5013b31e040d80004','心的影子（文/林清玄）','<p><img src=\"/blog/js/ueditor/jsp/upload/20121124/72551353751381441.jpg\" title=\"心的影子\" /></p><p><span class=\"Apple-style-span\" style=\"font-family:&#39;helvetica neue&#39;, &#39;lucida grande&#39;, verdana;line-height:23px;background-color:#FFFFFF;\"><span>文</span><span>/</span><span>林清玄</span></span></p><p><span class=\"Apple-style-span\" style=\"font-family:&#39;helvetica neue&#39;, &#39;lucida grande&#39;, verdana;line-height:23px;background-color:#ffffff;\"><br /><span>　　我相信命理，但我不相信在床脚钉四个铜钱就可以保证婚姻幸福、白首偕老。</span><br /><br /><span>　　我相信风水，但我不相信挂一个风铃、摆一个鱼缸就可以使人财运亨通、官禄无碍。</span><br /><br /><span>　　我相信人与环境中有一些神秘的对应关系，但我不相信一个人走路时先跨走左脚或右脚就可以使一件事情成功或失败。</span><br /><br /><span>　　我相信除了人，这世界还有无数的众生与我们共同生活。但我不相信烧香拜拜就可以事事平安、年年如意。</span><br /><br /><span>　　我相信人与人间有不可思议的因缘，但我不相信不经过任何努力，善缘就可以成熟；不经过任何奋斗，恶缘就能消失。</span><br /><br /><span>　　我相信轮回、因果、业报能使一个人提升或堕落，但我不相信借助于一个陌生的算命和改运，就能提升我们或堕落我们。</span><br /><br /><span>　　我相信上帝和天神能对人有所助力，但我不相信光靠上帝和天神可以使我们进入永恒的天国，或因不信，就会使我们落入无边的地狱。</span><br /><br /><span>　　这些相信与不相信，是缘于我知道一切命运风水只是心的影子，一切际遇起落也只是心的影子。心水如果澄澈，什么山水花树在上面都是美丽的；心水如果污浊，再美丽的花照在上面也只有污秽的东西。</span><br /><br /><span>　　因此，改造命运的原理是要从心做起，而改造命运的方法是进入正法，不要落入外道。“心内求法就是正法，心外求法既是外道，”迷信也是如此，想改变才是正信——所以迷信不应指命运、风水、鬼神等神秘的事物，迷信是指心被向外追求的意念所障蔽和迷转了。</span><br /><br /><span>　　佛经里说：“佛能空一切相，成万法智，而不能灭定业。”佛不能灭的定业，谁能灭呢？只有靠自己了。《金刚经》也说：“若以色见我，以音声求我，是人行邪道，不能见如来。”——什么才能见如来呢？心才能见如来，所以应先求自己的心。</span><br /><br /><span> &nbsp; &nbsp; &nbsp;一个人如果澄清了，就日日是好日，夜夜是清宵，处处是福地，法法是正法，那么，还有什么能迷惑、染着我们呢？</span></span><br /></p>',13,'2012-11-24 18:04:38','2012-11-24 18:05:20',1,'<p><span class=\"Apple-style-span\" style=\"font-family:&#39;helvetica neue&#39;, &#39;lucida grande&#39;, verdana;line-height:23px;background-color:#ffffff;\"><span>我相信命理，但我不相信在床脚钉四个铜钱就可以保证婚姻幸福、白首偕老。</span><br /><br /><span>　　我相信风水，但我不相信挂一个风铃、摆一个鱼缸就可以使人财运亨通、官禄无碍。</span><br /><br /><span>　　我相信人与环境中有一些神秘的对应关系，但我不相信一个人走路时先跨走左脚或右脚就可以使一件事情成功或失败。</span></span><br /></p>');
INSERT INTO `article` VALUES ('929288823a1aafea013a1ab320c70000','（转）要么滚回去，要么拼命','<p><span style=\"color:#111111;line-height:21px;font-size:14px;\">以前在墨尔本的一个室友，突然打电话给我，在我这里马上要凌晨3点的时候。他让我猜他现在在哪里，我说不是在墨尔本嘛，你还能去哪。他很神秘地说，不是哦，我现在在西班牙。然后我一下子就愣住了。因为很久之前我在一个人人相册里看到有关西班牙的照片的时候，曾经跟他说，西班牙那么漂亮自己将来一定要去一次。 我没有想到的是，在我就要把自己曾经一闪而过的想法忘记的时候，他的电话就这么来了。到最后，站在我最想去的地方的人，却不是我。</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">挂了电话之后酷我音乐盒正好放到阿姆的lose yourself，依旧是那熟悉的节奏，和他的那段：look, if you had, one shot, or one opportunity, to seize everything that you ever wanted - one moment. Would you capture it or just lst it slip. 不知道为什么脑海里浮现的是《当幸福来敲门》，是男主角最穷困潦倒的时候在车站的厕所里过夜，是他身上只有20每分的日子，可是他从来就没有放弃过。</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">如果你有梦想，就一定要捍卫它。</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">老爸同事的女儿，比我大三届，我刚进那个高中的时候她已经出国两年了，正好我们的老师是一样的。高二的时候我们老师给我们读了一封信，是她从英国寄回来的。她说现在过得很好，谢谢老师当年的教导，然后张新宇（高中的班主任）慢慢地念出信的最后几个字——来自剑桥。当时一下子就懵了，对那种学校也只有敢想的份了，后来我才知道原来这是我老爸同事的女儿。老爸总是感慨地对我说，一个女生，能那么优秀真的很不容易。后来有幸跟她见面，她说的一句话我至今记忆犹新，她说，因为想要过自己的人生吧，很多事情就像是旅行一样，当你决定要出发的时候，最困难的那部分其实就已经完成了。</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">突然就想到了自己，第一次出国的时候，离自己的17岁生日还差3个月。奇怪的是在机场的时候，我并没有想象中的那么不安，我只是反复告诉自己，这条路是你自己选的，不管怎么样，也要走下去。可是留学生活并没有想象中的那么顺利，恋爱也是无疾而终毕竟隔着那么远的距离，一时兴起去打工却因为太累最后还是辞职了。</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">后来有一天在fb上看到Leo，一个澳洲本地小伙，成绩好到令人发指，最可贵的是他的性格还很好，做事能力好到让人嫉妒。我就开始跟Leo聊起最近的生活，到后来就变成了我的诉苦。等我说完了很多，过了很久，我才看到他打过来的字，他说，我到现在都用不起iphone这种在你们那里随手可见的东西，我现在的学费都是自己赚的，虽然你离家很远但是你父母一直在后面资助你，你每天就做这么一点事情，你凭什么说自己撑不下去了，你有资格么，那些比你累的人都没有说什么，那些比你优秀的人比你努力的多，你有什么资格在这里唉声叹气？！</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">然后他对我说了一句我到现在还一直记着的话：要么滚回家里去，要么就拼。（go home or stand up, it&#39;s your fucking choice. Do you still remember the reason why you are here?!）</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">我突然间就醒了，我一直只看到那些闪闪发光的人身上的闪光点，却不知道他们到底是用了一个什么样的代价，才换取了这样的一个人生。我又有什么资格在这里抱怨。我为什么要出国，在那个时候义无反顾的自己，怎么现在反而后悔了呢。什么时候起，那个有着梦想的自己就死了？</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">我一直觉得自己的青春很苦逼，老是在想这么下去会不会有未来。自始至终也没能对这个不属于我的城市产生过一丝归属感，很多想法都只是一闪而过。为什么明明知道时间那么少，青春那么短，想的最多的，不是怎么样去接近梦想，而是反复的不安疑惑？</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">终于觉得，我的苦逼，熬夜，都会在最终让我迎来属于我的结局。从我离开家的那一刻起就注定了我无法回头的青春。记得上次一夜没睡跟朋友去山上看日出，偶然听他们说起自己之前的生活，才明白不管是表面多么快乐优秀的一个人，不管是外表多么光鲜漂亮的一个人，都有各自的心结和苦逼的过去。就像是青春注定要漂泊和颠沛流离一般，那些流过的泪受过的苦，总有过去的一天，又有谁的青春不曾苦逼过？</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">一个人二十岁出头的时候，除了仅剩不多的青春以外什么都没有，但是你手头为数不多的青春却能决定你变成一个什么样的人。往往你将来成为一个什么样的人，就在于在这个阶段你想要什么。一个人一辈子能去往几个想去的地方，能看过几个难忘的风景，能读到几本改变你人生的文字，又能经历多少次难忘的旅行。这个世界那么多不顺心的事情又能怎么样，对他们说一句fuck you，然后继续努力做好自己应该做的事情。</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">就像阿姆歌里唱的那样，我不能在这里变老。我要在变老之前，做一些到了80岁还会微笑的事情。</span><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><br style=\"color:#111111;line-height:21px;font-size:13px;\" /><span style=\"color:#111111;line-height:21px;font-size:14px;\">我想，一个人最好的样子就是平静一点，哪怕一个人生活，穿越一个又一个城市，走过一个又一条街道，仰望一片又一片天空，见证一次又一次别离。然后在别人质疑你的时候，你可以问心无愧地对自己说，虽然每一步都走的很慢，但是我不曾退缩过。</span></p><p><span style=\"font-size:14px;\"><br /></span></p><p><span style=\"font-size:14px;\">原文链接：</span><a href=\"http://www.douban.com/note/202750236/\" style=\"font-size:14px;text-decoration:underline;\"><span style=\"font-size:14px;\">http://www.douban.com/note/202750236/</span></a><span style=\"font-size:14px;\"> </span></p><p style=\"padding:0px;margin-top:0px;margin-bottom:0px;\"><br /></p>',178,'2012-10-01 13:01:17','2012-10-20 22:59:10',1,'<p><span style=\"color:#111111;line-height:21px;font-family:helvetica, tahoma, arial, sans-serif;background-color:#ffffff;font-size:14px;\" class=\"Apple-style-span\"> 以前在墨尔本的一个室友，突然打电话给我，在我这里马上要凌晨3点的时候。他让我猜他现在在哪里，我说不是在墨尔本嘛，你还能去哪。他很神秘地说，不是哦，我现在在西班牙。然后我一下子就愣住了。因为很久之前我在一个人人相册里看到有关西班牙的照片的时候，曾经跟他说，西班牙那么漂亮自己将来一定要去一次。 我没有想到的是，在我就要把自己曾经一闪而过的想法忘记的时候，他的电话就这么来了。到最后，站在我最想去的地方的人，却不是我。</span><br /></p>');

#
# Source for table comment
#

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` varchar(32) NOT NULL DEFAULT '' COMMENT '评论标识',
  `comment_content` mediumtext COMMENT '评论内容',
  `name` varchar(255) DEFAULT NULL COMMENT '评论者姓名',
  `email` varchar(255) DEFAULT NULL COMMENT '评论者的邮箱',
  `url` varchar(255) DEFAULT NULL COMMENT '评论者友情链接',
  `create_date` datetime DEFAULT NULL COMMENT '评论创建日期',
  `rep_comment_id` varchar(32) DEFAULT NULL COMMENT '用于查询所归属的评论',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

#
# Dumping data for table comment
#

INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a4d6af8fb0000','现在还在开放测试，以后就会关闭','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-11 09:23:07',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a4da6c2120001','<p><img src=\"http://img.baidu.com/hi/jx2/j_0012.gif\" />还是这么冷清<br /></p>','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-11 10:28:25',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a4ff2037b0002','<p>是不是应该针对特定的评论有个回复功能？</p>','猪猪','d41d8cd98f00b204e9800998ecf8427e','','2012-10-11 21:09:51',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a507a0ca20003','<p>嗯。后面会加上去，一直会完善</p>','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-11 23:38:26',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a507a521a0004','<p>有什么好的建议，欢迎留言<br /></p>','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-11 23:38:44',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a50cffc350005','<p>还有，写博文 那里最好设计为 代码和视图可以互换的，这样有意用来打广告或宣传的人可以在Dreamware里面设计包含较多超链接的海报或广告，然后将代码粘过来发布，但发布出来显示的是视图。<br /></p>','猪猪','d41d8cd98f00b204e9800998ecf8427e','','2012-10-12 01:12:18',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a50de056f0006','<p>还有，其实现在的空间形式都差不多。我一直希望有个新玩法。开发一部分为电子杂志主体的，设计好摸板，博主自己在模板添加文字内容、图片、音乐、或视频。让自己的故事以电子杂志的形式展示及保存下来。<br /></p>','猪猪','d41d8cd98f00b204e9800998ecf8427e','','2012-10-12 01:27:38',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a52ab13d50007','<p><img src=\"http://img.baidu.com/hi/jx2/j_0016.gif\" />以上建议非常让我汗颜，容我周末再回去查下相关技术上的实现细节。<br /><br /></p>','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-12 09:51:14',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a52ae9f7b0008','<p>要做的事情还非常多，比如标签墙，比如rss订阅，又比如左边的个人栏。<br /><br />我的想法是，先看看现在主流的博客程序，都提供了些什么功能，实现完了之后，再加上自己的一些想法，让它更具个人的特色。<br /><br />无奈公司琐事繁多，平时时间也很紧，只能多利用周末时间来搞搞了。<br /></p>','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-12 09:55:06',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a55bfb3ea0009','<p>在完善基本的同时也可以添加个性，只有吸引更多的人来才有更多更好的建议，提到的第一点功能可以参考QQ邮箱的代码哦。<br /></p>','猪猪','d41d8cd98f00b204e9800998ecf8427e','','2012-10-13 00:12:37',NULL);
INSERT INTO `comment` VALUES ('2fa998b23a4d666f013a79e4c1c4000a','<p>找到一块少人的吐槽地，挺好。楼主别见怪，不想看就删掉。没发忍受社会的黑暗，很想去流浪，可是有太多东西放不下，或者这只是自己懦弱的借口。很多时候我会想，在这世上有多少人爱我？爱我多久？离开的话有多少人伤心？伤心多久？生活不知什么时候变成是别人的，而不是自己的。但是我仍然想挣扎着将日子过下去。因为当我的生活不是我的时候，我的生命也不是我的，只要还有人记得我，我就应该继续存在着。<br /></p>','猪猪','d41d8cd98f00b204e9800998ecf8427e','','2012-10-20 00:39:25',NULL);
INSERT INTO `comment` VALUES ('2fa998b23b28f1b5013b28fbfa9e0001','<a href=\"#2fa998b63b144b74013b25feb9330000\">@Lewis</a><br />This article is originally published by a girl who study in foreign country.You can get the url at the end of the article.<br /><br />BTW,if u can totally get the meaning of the whole article,your chinese must be very good!And what is the guy below named &quot;Valinda&quot; trying to do?<br />','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-11-23 00:38:20','2fa998b63b144b74013b25feb9330000');
INSERT INTO `comment` VALUES ('2fa998b23b28f1b5013b2900063f0002','<a href=\"#2fa998b63b144b74013b287e82e00001\">@Valinda</a><br />You flatter me if u want to advertise something here. Due to my laziness, this place is rarely visited. I think u might be disappointed.<br /><br />And can u contact me and tell me how u get here? It would be very appreciated.<br />','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-11-23 00:42:45','2fa998b63b144b74013b287e82e00001');
INSERT INTO `comment` VALUES ('2fa998b23b28f1b5013b31e55b6b0005','<p><img src=\"http://img.baidu.com/hi/jx2/j_0006.gif\" />沙发自己拿下。<br /></p>','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-11-24 18:10:12',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a273304013a273911590000','为各位各位各位高温各位各位','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-03 23:23:02',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a273304013a2739e1230001','巍峨各位各位个','相见欢','d41d8cd98f00b204e9800998ecf8427e','','2012-10-03 23:23:55',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a344516013a345805590000','垃圾','高富帅','b651d90372cc0f5c3aa8b509a598ff6b','erer','2012-10-06 12:31:54',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a344516013a345b62cc0001','。。。。。。这个评论是怎么回事','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-06 12:35:35',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a344516013a35228f540002','<p><img src=\"http://img.baidu.com/hi/tsj/t_0002.gif\" /><br /></p>','无名氏','d41d8cd98f00b204e9800998ecf8427e','','2012-10-06 16:13:08',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a344516013a35e293a80003','','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-06 19:42:52',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a344516013a35e305b80004','<p><img src=\"http://img.baidu.com/hi/tsj/t_0002.gif\" /></p>','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-06 19:43:21',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a344516013a35f3037a0005','<p><img src=\"http://img.baidu.com/hi/tsj/t_0012.gif\" />自己坐沙发好了。<br /></p>','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-06 20:00:49',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a344516013a487bde280007','<p>如果有梦想，一定要坚持。<br /></p>','猪猪','d41d8cd98f00b204e9800998ecf8427e','','2012-10-10 10:23:28',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a344516013a487fcfba0008','游客也有博主的权限的？','猪猪','d41d8cd98f00b204e9800998ecf8427e','','2012-10-10 10:27:46',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a8c4c08013a8c561be70000','<a href=\"#2fa998b23a4d666f013a79e4c1c4000a\">@猪猪<br /><br /></a>不要让这些消极的情绪支配了你，要多看到人生乐观和开心的一面，从你的朋友，你关心和你爱的人身上总能找到生活的动力，没有必要将你自己孤立起来，关心和在乎你的人，比你想象中要多得多，可能只是你没注意到而已。<br /><br />我曾经也有过迷茫和困惑的时候，现在我想通了，按自己的想法来活，不要活在别人的眼光和别人对你的设定下。人生能够顺着自己的心意，通过自己的努力和奋斗去争取，本来就是一件多么让人快乐的事情啊。希望我这些亲身的感悟对你有帮助。<br />','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-23 14:36:24','2fa998b23a4d666f013a79e4c1c4000a');
INSERT INTO `comment` VALUES ('2fa998b63a8c4c08013a94ac95440001','<p>谢谢你。分享了很多，谢谢。看到有回复功能了，惊喜！真对评论的回复是否也应该再有个回复呢^_^继续加油啊^_^<br /></p>','猪猪','d41d8cd98f00b204e9800998ecf8427e','','2012-10-25 05:27:49',NULL);
INSERT INTO `comment` VALUES ('2fa998b63a8c4c08013aa5ba32a90002','<a href=\"#2fa998b63a8c4c08013a94ac95440001\">@猪猪</a><br /><br />唉，现在这东西搞的有点乱，目前是往简单的方向做，要忙的事情太多了。<br /><br />','相见欢','4543d381c252c70925caa58a2921c347','http://lichhao.com/blog','2012-10-28 12:56:14','2fa998b63a8c4c08013a94ac95440001');
INSERT INTO `comment` VALUES ('2fa998b63b144b74013b25feb9330000','Short, sweet, to the point, FREE-exactly as ifnormtaoin should be!','Lewis','a52ff22f4900d24c1fddae345d88bc45','http://www.facebook.com/profile.php?id=100003407034129','2012-11-22 10:42:28',NULL);
INSERT INTO `comment` VALUES ('2fa998b63b144b74013b287e82e00001','<url>http://www.gettopedmeds.com/|cialis</url> 84128 <url>http://www.freelifeinsurquotes.com/|no exam life insurance</url> 96314 ','Valinda','bc24b6e8c28db3108d3010aef4478637','http://www.acnetreatment4you.com/','2012-11-22 22:21:17',NULL);

#
# Source for table comment_article
#

DROP TABLE IF EXISTS `comment_article`;
CREATE TABLE `comment_article` (
  `comment_id` varchar(32) DEFAULT NULL,
  `article_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论文章关系表';

#
# Dumping data for table comment_article
#

INSERT INTO `comment_article` VALUES ('2fa998b63a344516013a345805590000','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b63a344516013a345b62cc0001','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b63a344516013a35228f540002','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b63a344516013a35e305b80004','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b63a344516013a35f3037a0005','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b63a344516013a487bde280007','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b63a344516013a487fcfba0008','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a4d6af8fb0000','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a4da6c2120001','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a4ff2037b0002','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a507a0ca20003','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a507a521a0004','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a50cffc350005','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a50de056f0006','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a52ab13d50007','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a52ae9f7b0008','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a55bfb3ea0009','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23a4d666f013a79e4c1c4000a','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b63a8c4c08013a94ac95440001','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b63b144b74013b25feb9330000','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b63b144b74013b287e82e00001','929288823a1aafea013a1ab320c70000');
INSERT INTO `comment_article` VALUES ('2fa998b23b28f1b5013b31e55b6b0005','2fa998b23b28f1b5013b31e040d80004');

#
# Source for table tag
#

DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` varchar(32) NOT NULL DEFAULT '' COMMENT '标签标识',
  `tag_name` varchar(250) DEFAULT NULL COMMENT '标签名称',
  `create_date` datetime DEFAULT NULL COMMENT '标签创建日期',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

#
# Dumping data for table tag
#

INSERT INTO `tag` VALUES ('4028ec5439dcb0e70139dcc967bd0004','生活感悟','2012-09-19 12:29:10');
INSERT INTO `tag` VALUES ('4028ec5439dcb0e70139dcc968030005','新闻态度','2012-09-19 12:29:10');
INSERT INTO `tag` VALUES ('4028ec5439dcb0e70139dcc968330006','爱情絮语','2012-09-19 12:29:10');
INSERT INTO `tag` VALUES ('4028ec5439dcb0e70139dcc9ec580008','技术人生','2012-09-19 12:29:44');
INSERT INTO `tag` VALUES ('4028ec5439e3959c0139e397030d0001','胡言乱语','2012-09-20 20:11:28');
INSERT INTO `tag` VALUES ('4028ec5439e3959c0139e3cea4260004','职业规划','2012-09-20 21:12:14');

#
# Source for table tag_article
#

DROP TABLE IF EXISTS `tag_article`;
CREATE TABLE `tag_article` (
  `tag_id` varchar(32) DEFAULT NULL,
  `article_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签文章关系表';

#
# Dumping data for table tag_article
#

INSERT INTO `tag_article` VALUES ('4028ec5439dcb0e70139dcc967bd0004','929288823a1aafea013a1ab320c70000');
INSERT INTO `tag_article` VALUES ('4028ec5439e3959c0139e397030d0001','2fa998b23b28f1b5013b31e040d80004');
INSERT INTO `tag_article` VALUES ('4028ec5439dcb0e70139dcc967bd0004','2fa998b23b28f1b5013b31e040d80004');

#
# Source for table user
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(32) NOT NULL DEFAULT '0' COMMENT '用户标识',
  `user_name` varchar(250) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(250) DEFAULT NULL COMMENT '用户密码',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Dumping data for table user
#


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
