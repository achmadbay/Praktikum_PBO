import java.util.ArrayList;
import java.util.Scanner;

// Abstract class Produk
abstract class Produk {
    protected final int id;
    protected String nama;
    protected double harga;

    public Produk(int id, String nama, double harga) {
        this.id = id;
        this.nama = (nama.isEmpty()) ? "Tidak Diketahui" : nama;
        this.harga = (harga < 0) ? 0 : harga;
    }

    public final int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
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

    public abstract String deskripsi();

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Harga: Rp" + harga;
    }
}

// Subclass Parfum
class Parfum extends Produk {
    private final String aroma;

    public Parfum(int id, String nama, double harga, String aroma) {
        super(id, nama, harga);
        this.aroma = (aroma.isEmpty()) ? "Tidak Diketahui" : aroma;
    }

    public String getAroma() {
        return aroma;
    }

    @Override
    public String deskripsi() {
        return "Parfum Biasa: " + getNama() + " | Aroma: " + getAroma();
    }

    @Override
    public String toString() {
        return super.toString() + ", Aroma: " + aroma;
    }
}

// Subclass ParfumEdisiTerbatas
class ParfumEdisiTerbatas extends Parfum {
    private String edisi;

    public ParfumEdisiTerbatas(int id, String nama, double harga, String aroma, String edisi) {
        super(id, nama, harga, aroma);
        this.edisi = (edisi.isEmpty()) ? "Tidak Diketahui" : edisi;
    }

    public String getEdisi() {
        return edisi;
    }

    public void setEdisi(String edisi) {
        if (edisi.isEmpty()) {
            throw new IllegalArgumentException("Edisi tidak boleh kosong!");
        }
        this.edisi = edisi;
    }

    @Override
    public String deskripsi() {
        return "Parfum Edisi Terbatas: " + getNama() + " | Aroma: " + getAroma() + " | Edisi: " + getEdisi();
    }

    @Override
    public String toString() {
        return super.toString() + ", Edisi: " + edisi;
    }
}

// Final Main Program
public final class SistemPenjualanParfum {
    private static final ArrayList<Produk> daftarProduk = new ArrayList<>();
    private static int nextId = 1;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        tambahParfum("Tester Citrus", 120000, "Citrus");

        while (true) {
            System.out.println("\n=== Sistem Penjualan Parfum ===");
            System.out.println("1. Tambah Parfum");
            System.out.println("2. Tambah Parfum Edisi Terbatas");
            System.out.println("3. Tampilkan Produk");
            System.out.println("4. Edit Produk");
            System.out.println("5. Hapus Produk");
            System.out.println("6. Tampilkan Deskripsi Produk (Polymorphism)");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahParfum();
                case 2 -> tambahParfumEdisiTerbatas();
                case 3 -> tampilkanProduk();
                case 4 -> editProduk();
                case 5 -> hapusProduk();
                case 6 -> tampilkanDeskripsiProduk();
                case 7 -> {
                    System.out.println("Terima kasih telah menggunakan sistem ini!");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid, coba lagi.");
            }
        }
    }

    private static void tambahParfum() {
        System.out.print("Masukkan nama parfum: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan harga parfum: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Masukkan aroma parfum: ");
        String aroma = scanner.nextLine();

        daftarProduk.add(new Parfum(nextId++, nama, harga, aroma));
        System.out.println("Parfum berhasil ditambahkan!");
    }

    private static void tambahParfum(String nama, double harga, String aroma) {
        daftarProduk.add(new Parfum(nextId++, nama, harga, aroma));
        System.out.println("[OTOMATIS] Parfum berhasil ditambahkan!");
    }

    private static void tambahParfumEdisiTerbatas() {
        System.out.print("Masukkan nama parfum: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan harga parfum: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Masukkan aroma parfum: ");
        String aroma = scanner.nextLine();
        System.out.print("Masukkan edisi parfum: ");
        String edisi = scanner.nextLine();

        daftarProduk.add(new ParfumEdisiTerbatas(nextId++, nama, harga, aroma, edisi));
        System.out.println("Parfum edisi terbatas berhasil ditambahkan!");
    }

    private static void tampilkanProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia.");
            return;
        }
        for (Produk produk : daftarProduk) {
            System.out.println(produk);
        }
    }

    private static void editProduk() {
        System.out.print("Masukkan ID produk yang ingin diedit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Produk produk : daftarProduk) {
            if (produk.getId() == id) {
                System.out.print("Masukkan nama baru: ");
                produk.setNama(scanner.nextLine());
                System.out.print("Masukkan harga baru: ");
                produk.setHarga(scanner.nextDouble());
                scanner.nextLine();
                System.out.println("Produk berhasil diperbarui!");
                return;
            }
        }
        System.out.println("Produk dengan ID tersebut tidak ditemukan.");
    }

    private static void hapusProduk() {
        System.out.print("Masukkan ID produk yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = daftarProduk.removeIf(produk -> produk.getId() == id);
        if (removed) {
            System.out.println("Produk berhasil dihapus!");
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    private static void tampilkanDeskripsiProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("Tidak ada produk untuk ditampilkan.");
            return;
        }

        System.out.println("Deskripsi Produk:");
        for (Produk produk : daftarProduk) {
            System.out.println(produk.deskripsi());
        }
    }
}
