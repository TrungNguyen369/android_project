-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 13, 2021 lúc 04:46 PM
-- Phiên bản máy phục vụ: 10.4.13-MariaDB
-- Phiên bản PHP: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `thietbi`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(1, 1, 5, 'Điện thoại Samsung Galaxy Fold', 50000000, 1),
(3, 9, 20, 'Điện thoại OPPO Reno3 Pro', 12490000, 0),
(4, 10, 21, 'Laptop Gaming Acer Predator Helios 300 2019 PH315-52 7688', 89980000, 0),
(5, 11, 1, 'Điện thoại iPhone 11 Pro Max 512GB', 39990000, 0),
(6, 12, 21, 'Laptop Gaming Acer Predator Helios 300 2019 PH315-52 7688', 44990000, 0),
(7, 13, 20, 'Điện thoại OPPO Reno3 Pro', 12490000, 1),
(8, 14, 21, 'Laptop Gaming Acer Predator Helios 300 2019 PH315-52 7688', 44990000, 1),
(9, 14, 19, 'Laptop Gaming MSI GS66 Stealth 10SE 213VN', 46990000, 1),
(10, 14, 16, 'Laptop Dell Inspiron G7 7590 N7590Z', 45990000, 1),
(11, 15, 21, 'Laptop Gaming Acer Predator Helios 300 2019 PH315-52 7688', 44990000, 1),
(12, 15, 18, 'Điện thoại Xiaomi Mi Note 10 Pro', 13990000, 1),
(13, 16, 20, 'Điện thoại OPPO Reno3 Pro', 24980000, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(200) NOT NULL,
  `sodienthoai` int(11) NOT NULL,
  `email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `email`) VALUES
(1, 'A', 132, 'a@gmail.com'),
(2, 'B', 741852, 'anhB@gmail.com'),
(3, 'Anh B', 741852, 'anhB@gmail.com'),
(4, 'a', 123456789, 'a1251@gmail.com'),
(5, 'a', 123456789, 'a1251@gmail.com'),
(6, 'A.Trung', 123456789, 'trung@gmail.com'),
(7, 'aa', 12, 'sad@gmail.com'),
(8, 'A2', 123, 'a2@gmail.com'),
(9, 'nguyen van a', 123, 'ngvana@gmailc.om'),
(10, 'nguyen van b', 8383, 'vanb@gmail.com'),
(11, 'a', 21, 'a'),
(12, 'dc di a choi 1', 41241, 'dcdiaoi@gmail.com'),
(13, 'hi vong', 123, 'hivong334@gmail.com'),
(14, 'a trung', 123456789, 'trung1@gmail.com'),
(15, 'a dau', 88888, 'dau@gmail.com'),
(16, 'Trung', 909123123, 'asd@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(200) NOT NULL,
  `hinhanhloaisanpham` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhanhloaisanpham`) VALUES
(1, 'Điện thoại', 'https://img.icons8.com/officel/2x/iphone.png'),
(2, 'Laptop', 'https://img.icons8.com/emoji/2x/laptop-emoji.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(200) NOT NULL,
  `motasanpham` varchar(200) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`) VALUES
(1, 'Điện thoại iPhone 11 Pro Max 512GB', 39990000, 'https://cdn.tgdd.vn/Products/Images/42/210654/iphone-11-pro-max-512gb-gold-400x460.png', 'Để tìm kiếm một chiếc smartphone có hiệu năng mạnh mẽ và có thể sử dụng mượt mà trong 2-3 năm tới thì không có chiếc máy nào xứng đang hơn chiếc iPhone 11 Pro Max 512GB mới ra mắt trong năm 2019 của A', 1),
(2, 'Laptop Gaming Asus ROG Griffin G703GI E5132T', 108000000, 'https://product.hstatic.net/1000026716/product/gearvn_laptop_asusg703gi-e5132t.png', 'Laptop Gaming Asus ROG Griffin G703GI E5132T', 2),
(3, 'Điện thoại iPhone 11 Pro Max 256GB', 35900000, 'https://cdn.tgdd.vn/Products/Images/42/210653/iphone-11-pro-max-256gb-black-400x460.png', 'iPhone 11 Pro Max 256GB là chiếc iPhone cao cấp nhất trong bộ 3 iPhone Apple giới thiệu trong năm 2019 và quả thực chiếc smartphone này mang trong mình nhiều trang bị xứng đáng với tên gọi \"Pro\".', 1),
(4, 'Laptop Gaming Asus ROG Zephyrus S GX701GXR HG142T', 84990000, 'https://product.hstatic.net/1000026716/product/gearvn-laptop-gaming-asus-zephyrus-s-gx701gxr-hg142t-121_7b72a3781dc6497bb47468b331ff3ba6.jpg', 'ASUS ROG Zephyrus S GX701GXR-HG142T lập nên chuẩn mực mới cho các dòng laptop gaming siêu mỏng với hệ điều hành Windows 10. ROG Zephyrus S GX701GXR mang những khát vọng lớn hơn nữa với màn hình 17-inc', 2),
(5, 'Điện thoại Samsung Galaxy Fold', 50000000, 'https://cdn.tgdd.vn/Products/Images/42/198158/samsung-galaxy-fold-black-400x460.png', 'Sau rất nhiều chờ đợi thì Samsung Galaxy Fold - chiếc smartphone màn hình gập đầu tiên của Samsung cũng đã chính thức trình làng với thiết kế mới lạ.', 1),
(6, 'Laptop Gaming Acer Predator Triton 500 PT515-52 72U2', 79990000, 'https://product.hstatic.net/1000026716/product/triton_72u2_691b69f0e5f6465c90fc723d9a982836.jpg', 'Laptop Gaming Acer Predator Triton 500 PT515-52 72U2', 2),
(7, 'Điện thoại Samsung Galaxy Z Flip', 36000000, 'https://cdn.tgdd.vn/Products/Images/42/213022/samsung-galaxy-z-flip-chitiet-2-788x544.png', 'Cuối cùng sau bao nhiêu thời gian chờ đợi, chiếc điện thoại Samsung Galaxy Z Flip đã được Samsung ra mắt tại sự kiện Unpacked 2020. Siêu phẩm với thiết kế màn hình gập vỏ sò độc đáo, hiệu năng tuyệt đ', 1),
(8, 'Laptop Gaming Asus ROG Zephyrus S GX701GXR H6072T', 79990000, 'https://product.hstatic.net/1000026716/product/h6072ta_0658fc917030426180f66eca7a97adc2.png', 'Laptop Gaming Asus ROG Zephyrus S GX701GXR H6072T', 2),
(9, 'Điện thoại Samsung Galaxy Note 10+', 19990000, 'https://cdn.tgdd.vn/Products/Images/42/206176/samsung-galaxy-note-10-plus-blue-400x460.png', 'Trông ngoại hình khá giống nhau, tuy nhiên Samsung Galaxy Note 10+ sở hữu khá nhiều điểm khác biệt so với Galaxy Note 10 và đây được xem là một trong những chiếc máy đáng mua nhất trong năm 2019, đặc ', 1),
(10, 'Laptop Gaming MSI GE75 Raider 10SFS 076VN', 66990000, 'https://product.hstatic.net/1000026716/product/ge75-raider-10sfs-076vn_e96c080832544349aeeb097d4d9a7aa6.png', 'Laptop Gaming MSI GE75 Raider 10SFS 076VN', 2),
(11, 'Điện thoại OPPO Find X2', 23990000, 'https://cdn.tgdd.vn/Products/Images/42/198150/oppo-find-x2-xanh-400x460-1-400x460.png', 'Tiếp nối thành công vang dội của thế hệ Find X, OPPO chính thức ra mắt Find X2 với những đường nét thanh lịch từ thiết kế phần cứng cho đến trải nghiệm phần mềm, hứa hẹn một vẻ đẹp hoàn hảo, một sức m', 1),
(12, 'Laptop Gaming Lenovo Legion 7 15IMH05', 54990000, 'https://product.hstatic.net/1000026716/product/lenovo-legion-7_100f1d43f53343fda7f3c711fc9f1ea4.jpg', 'Laptop Gaming Lenovo Legion 7 15IMH05', 2),
(13, 'Điện thoại Huawei P40 Pro', 23990000, 'https://cdn.tgdd.vn/Products/Images/42/215773/huawei-p40-pro-400x460-3-400x460.png', 'Huawei P40 Pro là một trong 3 mẫu smartphone đầu bảng năm 2020 đến từ nhà Huawei. Chiếc máy sở hữu cụm 4 camera Leica chụp ảnh và quay phim đỉnh cao, thiết kế màn hình siêu tràn ấn tượng cùng hiệu năn', 1),
(14, 'Laptop Gaming MSI GE66 Raider 10SF 044VN', 54990000, 'https://product.hstatic.net/1000026716/product/msi-ge66-raider-10sf_4bdb2df016a14866a9414b9468682edf.jpg', 'Laptop Gaming MSI GE66 Raider 10SF 044VN', 2),
(15, 'Điện thoại iPhone SE 256GB', 17490000, 'https://cdn.tgdd.vn/Products/Images/42/222631/iphone-se-256gb-2020-261820-101806-400x460.png', 'iPhone SE 256GB 2020 cuối cùng đã được Apple ra mắt, với ngoại hình nhỏ gọn được sao chép từ iPhone 8 nhưng mang trong mình một hiệu năng mạnh mẽ với vi xử lý A13 Bionic, mức giá hấp dẫn hứa hẹn sẽ là', 1),
(16, 'Laptop Dell Inspiron G7 7590 N7590Z', 45990000, 'https://product.hstatic.net/1000026716/product/gearvn-laptop-dell-inspiron-g7-7590-n7590z-78878_1e567b59f19a4132991e7ea5c185227b.jpg', 'Laptop Dell Inspiron G7 7590 N7590Z', 2),
(17, 'Điện thoại BlackBerry KEY2', 14990000, 'https://cdn.tgdd.vn/Products/Images/42/171028/blackberry-key2-3-400x460.png', 'BlackBerry Key2 là một bản nâng cấp toàn diện cho chiếc KeyOne với rất nhiều thay đổi và điều đáng mừng là yếu tố đặc trưng nhất của dòng điện thoại BlackBerry là bàn phím vật lý cổ điển vẫn được giữ ', 1),
(18, 'Điện thoại Xiaomi Mi Note 10 Pro', 13990000, 'https://cdn.tgdd.vn/Products/Images/42/213590/xiaomi-mi-note-10-pro-black-400x460.png', 'Siêu phẩm tầm trung Xiaomi Mi Note 10 Pro, chiếc smartphone đầu tiên sở hữu ống kính độ phân giải 108 MP cùng hệ thống 5 camera độc đáo, công nghệ sạc siêu nhanh 30W đi kèm nhiều tính năng nổi trội kh', 1),
(19, 'Laptop Gaming MSI GS66 Stealth 10SE 213VN', 46990000, 'https://product.hstatic.net/1000026716/product/gs66-stealth-10se-213vn_603c3704b7094db5aadbb12ca482341a_aff7ffb0e6064122a02e97a34946b31c.png', 'Laptop Gaming MSI GS66 Stealth 10SE 213VN', 2),
(20, 'Điện thoại OPPO Reno3 Pro', 12490000, 'https://cdn.tgdd.vn/Products/Images/42/216172/oppo-reno3-pro-den-400x460-400x460.png', 'OPPO Reno3 Pro tiếp nối truyền thống dòng Reno, vẫn sở hữu cụm camera sau chất lượng cao và bổ sung tính năng quay video Siêu chống rung 2.0 ấn tượng. OPPO cũng trang bị cho máy cụm camera selfie kép ', 1),
(21, 'Laptop Gaming Acer Predator Helios 300 2019 PH315-52 7688', 44990000, 'https://product.hstatic.net/1000026716/product/gvn-laptop-predator-helios-7688-0_b68b423cdab2487a87e254ee91a1c6d0.png', 'Laptop Gaming Acer Predator Helios 300 2019 PH315-52 7688', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD UNIQUE KEY `id` (`id`,`madonhang`,`masanpham`,`tensanpham`,`giasanpham`,`soluongsanpham`),
  ADD KEY `madonhang` (`madonhang`),
  ADD KEY `masanpham` (`masanpham`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idsanpham` (`idsanpham`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_ibfk_1` FOREIGN KEY (`madonhang`) REFERENCES `donhang` (`id`),
  ADD CONSTRAINT `chitietdonhang_ibfk_2` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`id`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`idsanpham`) REFERENCES `loaisanpham` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
