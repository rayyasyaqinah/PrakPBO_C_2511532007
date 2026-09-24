package pekan3_2511532007;
import java.util.ArrayList;
import pekan2_2511532007.Transaksi;
public class Rekening {
	// 1. Mengunci atribut dengan private
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private double nominal;
	private String pin; // Data sensitif!
	
	// Implementasi Asosiasi (1-to-many)
	private ArrayList<Transaksi> riwayatTransaksi;
	
	// 2. Modifikasi Constructor untuk menerima PIN awal
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		// Validasi PIN di dalam Constructor
		if(pinAwal.length() == 6) {
			this.pin = pinAwal;
		} else {
			System.out.println("peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
			this.pin= "123456";
		}
		this.riwayatTransaksi = new ArrayList<>();
	}
		// 3.Getter untuk atribut yang diizinkan dibaca publik
		public String getNomorRekening() { return nomorRekening; }
		public String getNamaPemilik() {return namaPemilik; }
		
		// 4. Method Otentikasi Internal (Validasi Enkapsulasi)
		public boolean otentikasi(String inputPin) {
			return this.pin.equals(inputPin);
		}
		public boolean gantiPin(String pinLama, String pinBaru) {
		    if (!otentikasi(pinLama)) {
		        return false;
		    }
		    if (pinLama.equals(pinBaru)) {
		        return false;
		    }
		    pin = pinBaru;
		    return true;
		}
		// ... {method setorTunai, tarikTunai, cekInformasi, cetakMutasi tetap dipertahankan seperti Modul 2)
public void setorTunai(double nominal) {
	if (nominal > 0) {
		saldo += nominal;
		// Merekam riwayat (Pembuatan objek Transaksi di dalam method)
		String idTrx = "TRX-S-" + System.currentTimeMillis();
		Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
		riwayatTransaksi.add(trxBaru);
		
		System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
	} else {
		System.out.println("Gagal: Nominal setor harus lebih dari 0!");
	}
}
public void tarikTunai(double nominal) {
    if (nominal < 10000) {
        System.out.println("Gagal: Nominal tarik tunai minimal Rp10000!");
    } else if (nominal > saldo) {
        System.out.println("Gagal: Saldo tidak mencukupi!");
    } else {
        saldo -= nominal;
    	// Merekam riwayat (Pembuatan objek Transaksi di dalam method)
		String idTrx = "TRX-T-" + System.currentTimeMillis();
		Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
		riwayatTransaksi.add(trxBaru);
        System.out.println("Tarik tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
    }		
	}
public void cetakMutasi() {
	if(riwayatTransaksi.isEmpty()) {
	System.out.println("Belum ada transaksi pada rekening ini");
    } else {
    	for (Transaksi transaksi : riwayatTransaksi) {
            transaksi.cetakDetail();
	}
    }
    }
	public void transaksiTerbaru() {
	    if (riwayatTransaksi.isEmpty()) {
	        System.out.println("Belum ada transaksi pada rekening ini.");
	        return;
	    }
	    System.out.println("3 transaksi terbaru");
	    int mulai = Math.max(0, riwayatTransaksi.size() - 3);
	    for (int i = riwayatTransaksi.size() - 1; i >= mulai; i--) {
	        riwayatTransaksi.get(i).cetakDetail();
	    }
}

public void cekInformasi() {
	System.out.println("--- INFO REKENING ---");
	System.out.println("No. Rekening : " + nomorRekening);
	System.out.println("Nama Pemilik : " + namaPemilik);
	System.out.println("Saldo Akhir  : Rp" + saldo);
	System.out.println("----------------------"); 
}
}


		
