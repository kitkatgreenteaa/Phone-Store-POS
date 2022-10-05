-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 14, 2022 at 06:31 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hpuas`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productID` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `sellprice` int(11) NOT NULL,
  `basePrice` int(11) NOT NULL,
  `picture` blob NOT NULL,
  `qty` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `name`, `sellprice`, `basePrice`, `picture`, `qty`) VALUES
('60xVKofTc5', 'BasicPhone', 6000, 4000, 0x89504e470d0a1a0a0000000d49484452000000370000003708030000009f052274000000097048597300000dd700000dd70142289b7800000300504c544547704c0278aa0199d10177a90178aa0178aa0199d10178aa0278aa0537500278aa002a490197cf0199d10177aa002b490198d008354c05344c09364c0199d10199d005344c0198d10177a906334b0072a70076a90297cf0198d00199d00198d105344c0299d10168950196cd0379aa0199d10278aa0299d10178aa0199d10299d1017aad019ad2019ad3083d560833490081b40391c70199d00199d00177aa0178a90278aa0299d10278aa0278aa0278aa0278aa0278aa0299d10299d10178aa0299d10299d00199d00299d10299d10299d10294cb0298d0017aad026995037aa8026995036995062e44053b55019dd704597c062d43019dd7045b7f06293d06334c06344c06334c06344c06344c06344c0296ce05364f0298d0019ed8063047019ed80281b106344c054a690298d00278aa0299d1ffce780299d0017eb3027baf0379aafecd780071a6fccd780075a8ffce770499cf34a9c502a1ddffd67a0636500177a9097fae0074a8fbcc770096d6bda56c06354e067cac30a6c32a9fbf006aa206364f2ba0c00073a7fccc77ffd87aa496670076a90076a8087eae0c82af0069a239aec8019fda027bae01a0db4cc9eb0074a685f9f182f5ee0068a12fa4c20067a00198d1ffd07402a3df0378a90075ac029dd7fbcd79029dd76ecad545caf1063149057bac2da2c182f4ee2499bc89fbf2ffd073ffd172ffd87ba395684fcef02ca8d106354d1c91b8289dbe269bbd2ea3c20e84b032a7c4cec3890098d1027db20181b70097d402a4e0388a9f0073abfacb770070a9029cd5029ad2a6bb976dc9d48eb69f46cbf2f8cb78047aab0098d777b3aaffcf71054e6f0073a50f85b280f3ed2ca1c187faf17ef1ed84f6ef36abc6d0c488cac28afccd79cac38ac3c18c3c8c9f36889dc4c18cb7bf9136879c3b8c9f036f9d257e9b469aadb9bf902a819c4094a70c8abeffd0720176a96bc6d2036c9a0381b6178dba42c6ee4fcced4cc8eac9a666baa56d42bfe4ffda75429eb0028ec3379ab052d1f2ffdc753ab6ddae966253d2f30c83b306344c29a4ce05507227a1cb2398bb84f7ef8d89fe910000006574524e5300fbfefe4d6e0303fe03fdfe02fcfdfefefb4dfdfdfc6e4dfb6efefe016efefed6fbfe04fdc74fbffc2586fcf2fbfcfcfefccab096fb31c0f8016d7c4c649d702eaf3ced15b372a06e6e6e4dfb6e6e6e6d4d4d4c6e4a0273f16da8fe6e6afcd719f9defd424bbb7d53000003484944415448c76360200244fd8a08650863201924d59486337092ae4f616d632499fac488d027c3c3a6cc2303652a039944e983ea40b1af91b03e1e060617275b2d3b6375552355735777add89484f885a59204f4f130683aeaec2bde595cdc0b02fd40d63e9dc4e48531f8f501b539cfdccfa40804bcbca2bca22086c8fe5edd3f2184dca95eac28cfc4c424cf9406024c026902024cd1719ffcf0eae3617048639263924b430619194c8afd5e0cec78f4b13188cf1191634a4303028ac56e403902fa30b481f4c912d62787a18f89287df219e8409e287d0285209091317dfaf48c0c309b3877ee3b5605043bb602756ddd01621e9ba3db4f485f50bfa1c1e7e3870edcba7679cba52d576f3e3870e8f84703c399fe78f57132049fd4e3fefef5c48a15cb97cf0082e54f569cf8f0965bef6400de78e764085ca8cfbd6ae591b973afe481c1dcb9474adef1eb2ff425a04fa85198bbadb5321d012a4baaf9054bc508e9ab19fefa56addc93830024d95780002d207d8d92c4da0789be3ce2ec6367f07eaea474effaae1b8f9ecd3f0804f3efdfde75e7a192cf5d4fbcf99d9d812553bb6ff6eed97b5fbc3abc00080e3fde0be4f5696ff720a4af8f8f79d6acec8ed36f2600e19bd72f2766cf9ac52cb18d9590be2c3e8ed4d4d4b2b26f3f56af5efde57ded53208f833193387d65a91d9d7fd7ac59f3fb673e904d9c3e2ee6d4ecececd48e5313274e3c950f663313675f596e6e6e7e6a6a73737359763b90ddce2141947d47ffd5af2fcf5fb4b47e43797eed86fa0db5d2c4d8a722b56ef292653d0d4b2f2c59b6b9a17ed292734db944f94fbab6aee7e2e98af2cd3d1737359717f514754913e7bff629d3a63567e74f9936a539bb02485610e9bf45eba72eeecaafad9f7ab63cbfb3696a5327d1fe9b0cf2dfffc9cb8a48f11f73ede2a2cd9b2a3a3716d57555944f2daa2b27d27f1d0ddd409fe537747703fdd7dddd4db4ff9a8aea3655006d05d9b7b1686a39b1fe3b737e12d07f93cf4f02faefc2f949c4fa6fd1faba8da0f0ac03866a6753ddd94e6952d227286576a492923ec179a00c89642636ffa1010e3aeb23c29d36595cb330edd3c8b422a04fad4f83838319551747aae9367bbcfa7818ccb2fab830ed4bddae86571f832583b505c80254fb667199a02b04004e1df1592c63d0760000000049454e44ae426082, 8),
('jf9021uhj9', 'SmartPhone', 16000, 12000, 0x89504e470d0a1a0a0000000d49484452000000370000003708030000009f052274000000097048597300000ec400000ec401952b0e1b00000300504c544547704c453e43453e43685e66685e66463b3f695b63433c42675b63453e43695e65685d65484045443d42423c40463f44463f44463f44453e43453c40494147463e43463f44695e65695e66473f45645a61685d65685e664d444a695b626b5b626a5c63695e6662d8f9453e4349434870646d67dafa00befa41474f68687260d7f862ceed06b7ed443d423fcef7685d6562d7f8685e6763d8f961d7f9695a6167707b62d8f800c1fd63d7f8675f67645a61487d9562d3f361d9fa6e585e473f454a42485dd6f861d8f959d6f85dd7fe61dafb6f646c5edafc06bef54fd3f85ed7f86a5f6778ddf908bff561dbfd63dafa433b4065d6f658dfff463f4464d8f858d6f951494f67d9f95dd7f9f7fbff5cdbfceff9feffe256ff5b66ff5e6957d7ffaeeafc635960655a6258dcfeff949980def9f0f9fe62d7f7fffeff52ddfff96470a5a4bc9cadc66e585d52d4f84740454b444949424760d7f99be6fbe7dde05cd7f9e2dde1c4effddedce2f6999feadde058defff9989efbe15df2faff5ad7fff76572d7df815ed7f9ff9296a1e7fb59d7fb76d9e4f9fbff89c7e168d5f4ab9fb69ea9c2f5e1639edbbb69616a695c63fde25affe251f8e15f56e0ff6adaf9fe606c3ecff9fcfcff00c1fca9e8fb07b7ec59505658d5f851474c73d8f5bbdbe8cedce55fd9fb5adcfec8f0fde99fa8c0dbe7fafcffa4e7fb7cd9f46fd0effd969cad9fb5d9dce375c9e7686c77424349675e6792e3fbff525bffe24d7fddf84047506d5a61676c7770d8f69bdbbeafddaa8edacc56718302bbf39adaeeff5660abdcae8ae1fad4f3fd93e3fb487c9400c5ff60657160565d3f393e8bd9f16959618ad9f172d0eea3bcd1c2afbfabb8cccbabb9b7b3c5cfdce460d3f3b8b3c47ecbe7a0a7c000bef96866700fa8d7d0dce440c9f2626e7c4b525b65d8f563c1dd74d9e5a8bace76d9e355454957d5f8e4a0aaf29ba272d2ee9fa8c181cbe552daff80dada62d1f055d8ffb8ecfcffe347415f6f86e0fa70cdecdcdf7ccff2fdc0eefc47809906b4e9467f9705b8ed00bef72dc9f742cef704c0f80db8ec5acced978c689c0000002174524e5300fbfefb55fafb04037cfefe01fafe596cad7efa13c34d8e42fb56555807fbfbfbda77bd43000003544944415448c763604002e28a5cdc5c8a98405fdf3b8c2f5f5096011be0609070c3aa0baa8fb14080811dab3e193766452e5cf60530160ae1d0c7e2c6cc65881d80ec73e0c4a54f9599bbff940936b06d2e7e7dfd374cb1813b7bee06e0d3c7d6773fdd06035ceebcfdebe67a613cfae434dff839d96200b569ea7bb7b2aae0b14f53db35dd201d040cb40c60402b5ceffa2c0d42fa0c8c5d40c0d8cc096880858113449f0e417d5af7dadada1e3d4837363058949cbcd0c289387dfef1bd313d3d3d9376d7a7bb775c39bf32cac289487dbb36d74ea89dd85b1ff53025a579c99985c4da7760e2a4dada989d6f9f3f5b5559d97c31d982047d936276be7a59535353997221399d5877be8f8989d9dc1bdff9b4b9b2a6f92cb1faea777f6e0482d72e511d4b52569d23363c5dd35d20c0cc22aab3e3aabbad811391f1971e0802e94e4a21b68ba242cc88b6cf3da83a282868a14191f31c67e7398e4a5ac4e9735f9b0d029f1686172f6f69599eeaa544943ea3c9ab37c5c5c6eecfc9dc12b9ac3d3263f11c23e2f40575c5366435c401f545034164b93391fa2683f46581f5d5a545b713ad8f6cfb40fe8b23d97fae0bd75eebee7ed1bd7261f8c6622020363cc1f1575d0d893f20203afeb40c80892530d0c0c9cc080cb488b44fcb2c2222a4b4c9ccd84c090a88d36751ba62e9adf9f322cc8c49d3e7b5634d4959d582f022d2ecf30fdd575551515172ba953c7d2749d447ae3bc1e172898c7001c643533819f160606c66e61402b40d18e74a5a20d29838fb94bc1c1d1d81a904487a4148e2ecd39ae2e3e313ee150e24a74048e2ecf3fa32bd2ea33cef6b46ddf4434fde8148a2e2cfc26c63c2cc84838e0781e461af8f2032fc0f417dff5be7ccc9f3f4f4cc73069360b6a7e36ffcf5ad9bfcb17f7f3d3c3c7c7d7d51c9bdbf7ee2abdfdd148ecd3a3ac31c13cc38fa1d6f7b82b97fcf8f23d698e0c8b70f78db2fcc86db72ed60203717ceceb57b8c5f9fbea1251c18f6596e803237f485e1d127edc6ac8f008a49b31315611cef30fcf621da8ede8a05270af5bd61bcb978f449792759c1415262a16ef071a840d26c5d5ddcfaf80b24ed11205f5957391fca0e0ed065e2c5ad2f9f571719282331997471b777c50a18999491802e3287891747fb9a81815d3098890945279236a6605111061eb85a00c699f8a3534b4c1f0000000049454e44ae426082, 8);

-- --------------------------------------------------------

--
-- Table structure for table `transactiondetail`
--

CREATE TABLE `transactiondetail` (
  `transactionid` varchar(10) NOT NULL,
  `productID` varchar(10) NOT NULL,
  `quantity` int(11) NOT NULL,
  `memberId` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactiondetail`
--

INSERT INTO `transactiondetail` (`transactionid`, `productID`, `quantity`, `memberId`) VALUES
('1ZLYmVKMUc', '60xVKofTc5', 1, '0'),
('3Q8dVERPaj', '60xVKofTc5', 1, '0'),
('59w2IM3gbC', '60xVKofTc5', 6000, '0'),
('59w2IM3gbC', 'jf9021uhj9', 16000, '0'),
('fx6Pc35gzd', '60xVKofTc5', 6000, '0'),
('jR2EibSJj1', '60xVKofTc5', 6000, '0'),
('oPLxVy7MxY', '60xVKofTc5', 6000, '0'),
('RbYqyr4f5q', '60xVKofTc5', 6000, '0'),
('U4zWldQo7U', '60xVKofTc5', 6000, '0'),
('XiIFdRV8nz', '60xVKofTc5', 1, '0'),
('XiIFdRV8nz', 'jf9021uhj9', 2, '0'),
('xqvJ6VfWXV', '60xVKofTc5', 1, '0'),
('y0JJsCFE3g', '60xVKofTc5', 6000, '0'),
('zsOnyK3bhC', '60xVKofTc5', 1, '0');

-- --------------------------------------------------------

--
-- Table structure for table `transactionheader`
--

CREATE TABLE `transactionheader` (
  `transactionid` varchar(10) NOT NULL,
  `staffid` varchar(10) NOT NULL,
  `transactiondate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactionheader`
--

INSERT INTO `transactionheader` (`transactionid`, `staffid`, `transactiondate`) VALUES
('1ZLYmVKMUc', 'wIcq7MnWa0', '2022-02-13'),
('3Q8dVERPaj', 'wIcq7MnWa0', '2022-02-14'),
('4cXiRxplWj', 'wIcq7MnWa0', '2022-02-14'),
('59w2IM3gbC', 'wIcq7MnWa0', '2022-02-13'),
('fx6Pc35gzd', 'wIcq7MnWa0', '2022-02-13'),
('hVaFomCs77', 'wIcq7MnWa0', '2022-02-14'),
('jR2EibSJj1', 'wIcq7MnWa0', '2022-02-14'),
('LszdOJaA8w', 'wIcq7MnWa0', '2022-02-14'),
('oPLxVy7MxY', 'wIcq7MnWa0', '2022-02-14'),
('RbYqyr4f5q', 'wIcq7MnWa0', '2022-02-13'),
('U4zWldQo7U', 'wIcq7MnWa0', '2022-02-14'),
('XiIFdRV8nz', 'wIcq7MnWa0', '2022-02-13'),
('xqvJ6VfWXV', 'wIcq7MnWa0', '2022-02-14'),
('y0JJsCFE3g', 'wIcq7MnWa0', '2022-02-13'),
('zsOnyK3bhC', 'wIcq7MnWa0', '2022-02-14');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userid` varchar(20) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `role` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `fullname`, `role`, `email`, `password`) VALUES
('IjgAWCz4gl', 'Felix Irwanto', 'Supervisor', 'spv@mail.com', 'spv123'),
('wIcq7MnWa0', 'Felix Irwanto', 'Cashier', 'cas@mail.com', 'cas123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`);

--
-- Indexes for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD PRIMARY KEY (`transactionid`,`productID`),
  ADD KEY `productID` (`productID`);

--
-- Indexes for table `transactionheader`
--
ALTER TABLE `transactionheader`
  ADD PRIMARY KEY (`transactionid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD CONSTRAINT `transactiondetail_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
