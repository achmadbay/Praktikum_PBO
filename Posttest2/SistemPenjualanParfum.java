import java.util.ArrayList;
import java.util.Scanner;

class Parfum {
    private final int id;
    private String nama;
    private double harga;
    private String aroma;

    public Parfum(int id, String nama, double harga, String aroma) {
        this.id = id;
        this.nama = (nama.isEmpty()) ? "Tidak Diketahui" : nama;
        this.harga = (harga < 0) ? 0 : harga;
        this.aroma = (aroma.isEmpty()) ? "Tidak Diketahui" : aroma;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public String getAroma() {
        return aroma;
    }

    public void setNama(String nama) {
        if (nama.isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong!");
        }
        this.nama = nama;
    }

    public void setHarga(double harga) {
        if (harga < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif!");
        }
        this.harga = harga;
    }

    public void setAroma(String aroma) {
        if (aroma.isEmpty()) {
            throw new IllegalArgumentException("Aroma tidak boleh kosong!");
        }
        this.aroma = aroma;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Harga: Rp" + harga + ", Aroma: " + aroma;
    }
}


public class SistemPenjualanParfum {
    private static final ArrayList<Parfum> daftarParfum = new ArrayList<>();
    private static int nextId = 1;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Sistem Penjualan Parfum Fantech ===");
            System.out.println("1. Tambah Parfum");
            System.out.println("2. Tampilkan Parfum");
            System.out.println("3. Edit Parfum");
            System.out.println("4. Hapus Parfum");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahParfum();
                case 2 -> tampilkanParfum();
                case 3 -> editParfum();
                case 4 -> hapusParfum();
                case 5 -> {
                    System.out.println("Terima kasih telah menggunakan sistem ini!");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }
    }

    private static void tambahParfum() {
        try {
            System.out.print("Masukkan nama parfum: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan harga parfum: ");
            double harga = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Masukkan aroma parfum: ");
            String aroma = scanner.nextLine();

            daftarParfum.add(new Parfum(nextId++, nama, harga, aroma));
            System.out.println("Parfum berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
            scanner.nextLine(); 
        }
    }

    private static void tampilkanParfum() {
        if (daftarParfum.isEmpty()) {
            System.out.println("Tidak ada parfum yang tersedia.");
            return;
        }
        for (Parfum parfum : daftarParfum) {
            System.out.println(parfum);
        }
    }

    private static void editParfum() {
        try {
            System.out.print("Masukkan ID parfum yang ingin diedit: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            for (Parfum parfum : daftarParfum) {
                if (parfum.getId() == id) {
                    System.out.print("Masukkan nama baru: ");
                    parfum.setNama(scanner.nextLine());
                    System.out.print("Masukkan harga baru: ");
                    parfum.setHarga(scanner.nextDouble());
                    scanner.nextLine();
                    System.out.print("Masukkan aroma baru: ");
                    parfum.setAroma(scanner.nextLine());
                    System.out.println("Parfum berhasil diperbarui!");
                    return;
                }
            }
            System.out.println("Parfum dengan ID tersebut tidak ditemukan.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
            scanner.nextLine(); 
        }
    }

    private static void hapusParfum() {
        System.out.print("Masukkan ID parfum yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Parfum parfum : daftarParfum) {
            if (parfum.getId() == id) {
                daftarParfum.remove(parfum);
                System.out.println("Parfum berhasil dihapus!");
                return;
            }
        }
        System.out.println("Parfum dengan ID tersebut tidak ditemukan.");
    }
}
