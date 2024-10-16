USE chamcong;
-- Tạo bảng NhanVien
CREATE TABLE NhanVien (
    MaNhanVien VARCHAR(10) PRIMARY KEY,
    TenNhanVien VARCHAR(100),
    SDT VARCHAR(15),
    Email_NV VARCHAR(100) UNIQUE 
);

-- Tạo bảng LoaiDonTu
CREATE TABLE LoaiDonTu (
    MaLoaiDonTu VARCHAR(10) PRIMARY KEY,
    TenLoaiDonTu VARCHAR(100)
);

-- Tạo bảng CaChamCong
CREATE TABLE CaChamCong (
    MaCa VARCHAR(10) PRIMARY KEY,
    TenCa VARCHAR(100),
    ThoiGianBatDau TIME,   
    ThoiGianKetThuc TIME 
);

-- Tạo bảng TaiKhoan
CREATE TABLE TaiKhoan (
    MaTK VARCHAR(100) PRIMARY KEY,      
    MatKhau VARCHAR(255),           
    HoTen VARCHAR(100),             
    Email_NV VARCHAR(100), 
    FOREIGN KEY (Email_NV) REFERENCES NhanVien(Email_NV) 
);
-- Tạo bảng DonTu
CREATE TABLE DonTu (
    MaDonTu VARCHAR(10) PRIMARY KEY,
    ThoiGianTao DATETIME,
    TrangThai VARCHAR(50),
    MaLoaiDonTu VARCHAR(10),  
    Email_NV VARCHAR(100),    
    PhanQuyen VARCHAR(10),  
    FOREIGN KEY (MaLoaiDonTu) REFERENCES LoaiDonTu(MaLoaiDonTu), 
    FOREIGN KEY (Email_NV) REFERENCES NhanVien(Email_NV) 
);

-- Tạo bảng ChamCong
CREATE TABLE ChamCong (
    MaChamCong VARCHAR(10) PRIMARY KEY,
    ThoiGianTao DATETIME,
    TrangThai VARCHAR(50),
    LoaiChamCong VARCHAR(50),
    ThoiGianTre VARCHAR(10), 
    MaNhanVien VARCHAR(10),
    MaCa VARCHAR(10),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
    FOREIGN KEY (MaCa) REFERENCES CaChamCong(MaCa) 
);


-- Insert dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (MaNhanVien, TenNhanVien, SDT, Email_NV) VALUES
('NV001', 'Nguyễn Văn Anh', '0901234567', 'nva@example.com'),
('NV002', 'Lê Thị Bình', '0902345678', 'ltb@example.com'),
('NV003', 'Trần Văn Cương', '0903456789', 'tvc@example.com'),
('NV004', 'Phạm Thị Dung', '0904567890', 'ptd@example.com'),
('NV005', 'Hoàng Văn Anh', '0905678901', 'hve@example.com'),
('NV006', 'Nguyễn Thị Lê', '0906789012', 'ntf@example.com'),
('NV007', 'Lê Văn Giang', '0907890123', 'lvg@example.com'),
('NV008', 'Trần Thị Hương', '0908901234', 'tth@example.com'),
('NV009', 'Phạm Văn Anh', '0909012345', 'pvi@example.com'),
('NV010', 'Hoàng Thị Kiên', '0910123456', 'htk@example.com');

-- Insert dữ liệu vào bảng TaiKhoan
INSERT INTO TaiKhoan (MaTK, MatKhau, HoTen, Email_NV) VALUES
('TK001', 'password1', 'Nguyễn Văn Anh', 'nva@example.com'),
('TK002', 'password2', 'Lê Thị Bình', 'ltb@example.com'),
('TK003', 'password3', 'Trần Văn Cương', 'tvc@example.com'),
('TK004', 'password4', 'Phạm Thị Dung', 'ptd@example.com'),
('TK005', 'password5', 'Hoàng Văn Anh', 'hve@example.com'),
('TK006', 'password6', 'Nguyễn Thị Lê', 'ntf@example.com'),
('TK007', 'password7', 'Lê Văn Giang', 'lvg@example.com'),
('TK008', 'password8', 'Trần Thị Hương', 'tth@example.com'),
('TK009', 'password9', 'Phạm Văn Anh', 'pvi@example.com'),
('TK010', 'password10', 'Hoàng Thị Kiên', 'htk@example.com');

-- Insert dữ liệu vào bảng LoaiDonTu
INSERT INTO LoaiDonTu (MaLoaiDonTu, TenLoaiDonTu) VALUES
('LDT001', 'Đi trễ/về sớm (trong vòng 1h)'),
('LDT002', 'Nghỉ không lương'),
('LDT003', 'Nghỉ phép - gửi trước 24h'),
('LDT004', 'Cưới/tang'),
('LDT005', 'Công tác'),
('LDT006', 'Làm việc từ xa'),
('LDT007', 'Giải trình công'),
('LDT008', 'Nghỉ có lương'),
('LDT009', 'Nghỉ ốm'),
('LDT010', 'Nghỉ thai sản');

-- Insert dữ liệu vào bảng CaChamCong
INSERT INTO CaChamCong (MaCa, TenCa, ThoiGianBatDau, ThoiGianKetThuc) VALUES
('CA001', 'Ca sáng', '08:00:00', '12:00:00'),
('CA002', 'Ca chiều', '13:00:00', '17:00:00'),
('CA003', 'Ca tối', '18:00:00', '22:00:00'),
('CA004', 'Ca sáng',  '08:00:00', '12:00:00'),
('CA005', 'Ca hành chính', '08:00:00', '17:00:00'),
('CA006', 'Ca sáng',  '08:00:00', '12:00:00'),
('CA007', 'Ca tối', '18:00:00', '22:00:00'),
('CA008', 'Ca chiều', '13:00:00', '17:00:00'),
('CA009', 'Ca hành chính', '08:00:00', '17:00:00'),
('CA010', 'Ca chiều', '13:00:00', '17:00:00');

-- Insert dữ liệu vào bảng DonTu
INSERT INTO DonTu (MaDonTu, ThoiGianTao, TrangThai, MaLoaiDonTu, Email_NV, PhanQuyen) VALUES
('DT001', '2024-01-01 08:00:00', 'Đã duyệt', 'LDT003', 'nva@example.com', 'NV003'),
('DT002', '2024-01-05 08:00:00', 'Chờ duyệt', 'LDT005', 'ltb@example.com', 'NV002'),
('DT003', '2024-01-10 08:00:00', 'Đã duyệt', 'LDT001', 'tvc@example.com', 'NV001'),
('DT004', '2024-01-15 08:00:00', 'Chờ duyệt', 'LDT006', 'ptd@example.com', 'NV005'),
('DT005', '2024-01-20 08:00:00', 'Đã từ chối', 'LDT004', 'hve@example.com', 'NV003'),
('DT006', '2024-01-25 08:00:00', 'Đã duyệt', 'LDT002', 'ntf@example.com', 'NV002'),
('DT007', '2024-02-01 08:00:00', 'Chờ duyệt', 'LDT003', 'lvg@example.com', 'NV001'),
('DT008', '2024-02-05 08:00:00', 'Đã từ chối', 'LDT005', 'tth@example.com', 'NV006'),
('DT009', '2024-02-10 08:00:00', 'Đã duyệt', 'LDT001', 'pvi@example.com', 'NV002'),
('DT010', '2024-02-15 08:00:00', 'Chờ duyệt', 'LDT007', 'htk@example.com', 'NV007');

-- Insert dữ liệu vào bảng ChamCong
INSERT INTO ChamCong (MaChamCong, ThoiGianTao, TrangThai, LoaiChamCong, ThoiGianTre, MaNhanVien, MaCa) VALUES
('CC001', '2024-01-01 08:00:00', 'Hoàn tất', 'Đi làm', ' ', 'NV001', 'CA001'),  
('CC002', '2024-01-05 08:15:00', 'Hoàn tất', 'Đi làm', 'Trễ 15p', 'NV002', 'CA002'),  
('CC003', '2024-01-10 08:30:00', 'Hoàn tất', 'Đi làm', 'Trễ 30p', 'NV003', 'CA003'),  
('CC004', '2024-01-15 08:45:00', 'Hoàn tất', 'Đi làm', 'Trễ 45p', 'NV004', 'CA001'),  
('CC005', '2024-01-20 09:00:00', 'Hoàn tất', 'Đi làm', 'Trễ 60p', 'NV005', 'CA002'),  
('CC006', '2024-01-25 08:05:00', 'Hoàn tất', 'Đi làm', 'Trễ 5p', 'NV006', 'CA003'),  
('CC007', '2024-02-01 08:20:00', 'Hoàn tất', 'Đi làm', 'Trễ 20p', 'NV007', 'CA001'),  
('CC008', '2024-02-05 08:10:00', 'Hoàn tất', 'Đi làm', 'Trễ 10p', 'NV008', 'CA002'),  
('CC009', '2024-02-10 08:35:00', 'Hoàn tất', 'Đi làm', 'Trễ 35p', 'NV009', 'CA003'),  
('CC010', '2024-02-15 08:50:00', 'Hoàn tất', 'Đi làm', 'Trễ 50p', 'NV010', 'CA001');


-- 1. Truy vấn để lấy thông tin về nhân viên và đơn từ của họ
SELECT NhanVien.TenNhanVien, DonTu.ThoiGianTao, DonTu.TrangThai
FROM NhanVien
JOIN DonTu ON NhanVien.Email_NV = DonTu.Email_NV;

-- 2. Truy vấn để lấy thông tin về nhân viên và ca làm việc của họ
SELECT NhanVien.TenNhanVien, CaChamCong.TenCa, ChamCong.ThoiGianTao, ChamCong.TrangThai
FROM NhanVien
JOIN ChamCong ON NhanVien.MaNhanVien = ChamCong.MaNhanVien
JOIN CaChamCong ON ChamCong.MaCa = CaChamCong.MaCa;

-- 3. Truy vấn để lấy thông tin về đơn từ và loại đơn từ
SELECT DonTu.MaDonTu, DonTu.ThoiGianTao, LoaiDonTu.TenLoaiDonTu
FROM DonTu
JOIN LoaiDonTu ON DonTu.MaLoaiDonTu = LoaiDonTu.MaLoaiDonTu;

-- 4. Truy vấn để lấy thông tin về ca làm việc và số lượng nhân viên làm ca đó
SELECT CaChamCong.TenCa, COUNT(ChamCong.MaChamCong) AS SoLuongNV
FROM CaChamCong
LEFT JOIN ChamCong ON CaChamCong.MaCa = ChamCong.MaCa
GROUP BY CaChamCong.TenCa;


SELECT NhanVien.TenNhanVien, ChamCong.ThoiGianTao, ChamCong.ThoiGianTre
FROM ChamCong
JOIN NhanVien ON ChamCong.MaNhanVien = NhanVien.MaNhanVien
WHERE CONVERT(INT, SUBSTRING(ChamCong.ThoiGianTre, 6, LEN(ChamCong.ThoiGianTre) - 6)) > 15;

