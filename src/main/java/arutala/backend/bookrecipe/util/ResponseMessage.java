package arutala.backend.bookrecipe.util;

public class ResponseMessage {
    public static final class Success {
        public static final String DEFAULT = "Success";
        public static final String USER_CREATED = "User %s registered successfully!";
        public static final String ADDED_TO_FAVORITE = "Resep %s berhasil ditambahkan ke dalam favorit";
        public static final String REMOVED_FROM_FAVORITE = "Resep %s berhasil dihapus dari favorit";
        public static final String RECIPE_UPDATED = "Resep %s berhasil diubah!";
        public static final String RECIPE_DELETED = "Resep %s berhasil dihapus!";
    }

    public static final class Failed {
        public static final String DEFAULT = "Error";
        public static final String INTERNAL_SERVER_ERROR = "Terjadi kesalahan server. Silakan coba kembali.";
        public static final String USERNAME_ALREADY_REGISTERED = "Username telah digunakan oleh user yang telah mendaftar sebelumnya";
        public static final String USERNAME_NOT_FOUND = "Username tidak terdaftar";
        public static final String WRONG_PASSWORD = "Kata sandi tidak sesuai";
        public static final String USERNAME_MALFORMED = "Format username belum sesuai";
        public static final String PASSWORD_MINIUM_SPECIFICATION = "Kata sandi harus memiliki minimal 6 karakter kombinasi angka dan huruf.";
        public static final String NAME_CONTAIN_SPECIAL_CHARACTER = "Format nama lengkap belum sesuai. (Tidak menggunakan special character dan maksimal 255 charackter)";
        public static final String COLUMN_MAX_CHARACTERS_EXCEEDED = "%s tidak boleh lebih dari %d karakter";
        public static final String EMPTY_COLUMN = "Kolom %s tidak boleh kosong";
        public static final String PASSWORD_LENGTH = "Kata sandi tidak boleh kurang dari 6 karakter";
        public static final String PASSWORD_CONFIRMATION_MISSMATCH = "Konfirmasi kata sandi tidak sama dengan kata sandi.";
        public static final String RECIPE_NOT_FOUND = "Resep masakan tidak ditemukan";
    }
}
