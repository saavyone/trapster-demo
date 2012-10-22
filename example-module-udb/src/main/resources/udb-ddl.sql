DROP TABLE IF EXISTS `trapster-demo`.`udb`; 
CREATE TABLE IF NOT EXISTS `trapster-demo`.`udb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT 'unknown',
  `uname` varchar(60) NOT NULL,
  `pwd` varchar(64) DEFAULT NULL,
  `new_password` varchar(64) DEFAULT NULL,
  `m_password` varchar(64) DEFAULT NULL,
  `level` int(1) unsigned NOT NULL DEFAULT '0',
  `info` varchar(200) NOT NULL DEFAULT 'none',
  `aradius` double NOT NULL DEFAULT '0.01',
  `alert` varchar(50) NOT NULL DEFAULT '',
  `signupdate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lastvotetime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `numvotes` int(11) NOT NULL DEFAULT '0',
  `karma` int(11) NOT NULL DEFAULT '0',
  `agreed` varchar(1) NOT NULL DEFAULT 'N',
  `tgpref` varchar(1) DEFAULT 'A',
  `pmpref` varchar(2) NOT NULL DEFAULT 'Y',
  `pmsmsaddr` varchar(50) NOT NULL,
  `global_moderator` int(11) NOT NULL DEFAULT '0',
  `logged_site` char(1) NOT NULL DEFAULT 'N',
  `newsletter` char(2) NOT NULL DEFAULT 'Y',
  `email_conf` char(1) NOT NULL DEFAULT 'N',
  `status` varchar(2) NOT NULL DEFAULT 'A',
  `lastkarma` int(11) NOT NULL DEFAULT '0',
  `sip` varchar(1) NOT NULL DEFAULT 'N',
  `loginfailures` int(11) NOT NULL DEFAULT '0',
  `modapplydate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lastapitime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lastwebtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `confirmdate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `oauthdate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `confirmmethod` varchar(2) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `uname_idx` (`uname`),
  KEY `status_idx` (`status`),
  KEY `sigdate` (`signupdate`),
  KEY `pmsmsaddr_idx` (`pmsmsaddr`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=103053285 ;
