import java.util.ArrayList;
import java.util.Scanner;
class Motor {
    private String merk;
    private boolean tersedia;

    public Motor(String merk) {
        this.merk = merk;
        this.tersedia = true;
    }

    public String getMerk() {
        return merk;
    }

    public boolean isTersedia() {
        return tersedia;
    }
    public void setMerk(String merk) {
        this.merk = merk;
    }
    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }
}
// Kelas turunan dari Motor untuk jenis Sport
class MotorSport extends Motor {
    public MotorSport() {
        super("CBR 150 RR");
    }

}

// Kelas turunan dari Motor untuk jenis Matic
class MotorMatic extends Motor {
    public MotorMatic() {
        super("Beat 150cc");
    }
}

class Pengguna {
    private String username;
    private String password;
    private boolean isAdmin;

    public Pengguna(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}


public class Main {
    private static ArrayList<Motor> daftarMotor = new ArrayList<>();
    private static ArrayList<Pengguna> pengguna = new ArrayList<>();
    public static void main(String[] args) {
        daftarMotor.add(new MotorSport());
        daftarMotor.add(new MotorMatic());

        // Menambahkan pengguna (admin dan user)
        pengguna.add(new Pengguna("admin", "admin123", true));
        pengguna.add(new Pengguna("user", "user123", false));

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            Pengguna penggunaAktif = null;
            for (Pengguna user : pengguna) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    penggunaAktif = user;
                    break;
                }
            }

            if (penggunaAktif != null) {
                if (penggunaAktif.isAdmin()) {
                    // Admin login
                    System.out.println("Selamat datang, Admin!");
                    while (true) {
                        System.out.println("Pilih operasi:");
                        System.out.println("1. Tambah Jenis Motor");
                        System.out.println("2. Lihat Daftar Motor");
                        System.out.println("3. Perbarui Jenis Motor");
                        System.out.println("4. Hapus Jenis Motor");
                        System.out.println("0. Keluar");

                        int pilihan = scanner.nextInt();

                        switch (pilihan) {
                            case 1:
                                System.out.print("Masukkan jenis motor baru: ");
                                scanner.nextLine(); // membersihkan buffer
                                String jenisBaru = scanner.nextLine();
                                daftarMotor.add(new Motor(jenisBaru));
                                System.out.println("Jenis motor baru ditambahkan.");
                                break;
                            case 2:
                                System.out.println("Daftar Jenis Motor:");
                                for (Motor motor : daftarMotor) {
                                    System.out.println(motor.getMerk());
                                }
                                break;
                            case 3:
                                System.out.println("Masukkan indeks jenis motor yang ingin diperbarui:");
                                int indeksPerbarui = scanner.nextInt();
                                if (indeksPerbarui >= 0 && indeksPerbarui < daftarMotor.size()) {
                                    System.out.print("Masukkan jenis motor baru: ");
                                    scanner.nextLine(); // membersihkan buffer
                                    String jenisPerbarui = scanner.nextLine();
                                    daftarMotor.get(indeksPerbarui).setMerk(jenisPerbarui);
                                    System.out.println("Jenis motor diperbarui.");
                                } else {
                                    System.out.println("Indeks tidak valid.");
                                }
                                break;
                            case 4:
                                System.out.println("Masukkan indeks jenis motor yang ingin dihapus:");
                                int indeksHapus = scanner.nextInt();
                                if (indeksHapus >= 0 && indeksHapus < daftarMotor.size()) {
                                    daftarMotor.remove(indeksHapus);
                                    System.out.println("Jenis motor dihapus.");
                                } else {
                                    System.out.println("Indeks tidak valid.");
                                }
                                break;
                            case 0:
                                System.out.println("Sampai jumpa!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                                break;
                        }
                    }
                } else {
                    // Pengguna biasa login
                    System.out.println("Selamat datang, " + penggunaAktif.getUsername() + "!");
                    // Implementasikan logika menyewa motor di sini
                    while (true) {
                        System.out.println("Pilih operasi:");
                        System.out.println("1. Lihat Daftar Motor Tersedia");
                        System.out.println("2. Sewa Motor");
                        System.out.println("0. Keluar");

                        int pilihan = scanner.nextInt();

                        switch (pilihan) {
                            case 1:
                                System.out.println("Daftar Motor Tersedia:");
                                for (int i = 0; i < daftarMotor.size(); i++) {
                                    Motor motor = daftarMotor.get(i);
                                    if (motor.isTersedia()) {
                                        System.out.println(i + ". " + motor.getMerk());
                                    }
                                }
                                break;
                            case 2:
                                System.out.println("Masukkan nomor jenis motor yang ingin disewa:");
                                int indeksSewa = scanner.nextInt();
                                if (indeksSewa >= 0 && indeksSewa < daftarMotor.size()) {
                                    Motor motorSewa = daftarMotor.get(indeksSewa);
                                    if (motorSewa.isTersedia()) {
                                        motorSewa.setTersedia(false);
                                        System.out.println("Anda telah menyewa motor jenis " + motorSewa.getMerk());
                                    } else {
                                        System.out.println("Motor tidak tersedia.");
                                    }
                                } else {
                                    System.out.println("Indeks tidak valid.");
                                }
                                break;
                            case 0:
                                System.out.println("Sampai jumpa!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                                break;
                        }
                    }
                }
            } else {
                throw new Exception("Login gagal. silahkan coba lagi!");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }finally{
            scanner.close();
        }
    }
}