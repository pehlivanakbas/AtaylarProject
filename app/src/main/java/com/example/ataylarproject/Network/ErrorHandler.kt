package com.example.ataylarproject.Network

import retrofit2.Response

open class ErrorHandler(
    val response: Response<*>? = null
) {
    open fun returnResponse(): ErrorModel? {
        return if (response == null)
            ErrorModel(ErrorType.NETWORK_ERROR, ToastMessage.connectionError())
        else
            when (response.code()){
                401 -> ErrorModel(ErrorType.SERVER_ERROR, ToastMessage.getUserError(), response.code(), response.errorBody())
                403 -> ErrorModel(ErrorType.FORBIDDEN_ERROR, ToastMessage.forbiddenError(), response.code(), response.errorBody())
                404 -> ErrorModel(ErrorType.NOT_FOUND_ERROR, ToastMessage.notFoundError(), response.code(), response.errorBody())
                405 -> ErrorModel(ErrorType.NOT_ALLOWED_ERROR, ToastMessage.notAllowedError(), response.code(), response.errorBody())
                500 -> ErrorModel(ErrorType.NOT_FOUND_ERROR, ToastMessage.serviceError(), response.code(), response.errorBody())
                else -> ErrorModel(ErrorType.NOT_FOUND_ERROR, ToastMessage.serviceError(), response.code(), response.errorBody())
            }
    }
}

class ToastMessage() {
    companion object {
        fun getUserError(): String {
            return "Kullanıcı adınızı ve şifrenizi kontrol ediniz."
        }

        fun connectionError(): String {
            return "Ağ bağlantılarını kontrol ediniz."
        }

        fun successMessage(): String {
            return "İşleminiz başarıyla gerçekleşmiştir."
        }

        fun serviceError(): String {
            return "Sistem kaynaklı bir hatadan dolayı şu anda işleminizi gerçekleştiremiyoruz."
        }

        fun notFoundError(): String {
            return "Sisteme erişim sağlanamadığından işleminizi gerçekleştiremiyoruz."
        }

        fun forbiddenError(): String {
            return "Sisteme erişiminiz engellenmiştir."
        }

        fun notAllowedError(): String {
            return "Bu işlemi gerçekleştirmek için yetkiniz bulunmamaktadır."
        }

        fun emptyBlank(): String {
            return "Lütfen boş alan bırakmayınız."
        }

        fun dateError(): String {
            return "Lütfen tarihleri kontrol ediniz."
        }

        fun locationCountingExist(): String {
            return "Seçilen mağazada lokasyon sayımı bulunmaktadır"
        }

        fun standCountingExist(): String {
            return "Seçilen mağazada reyon sayımı bulunmaktadır"
        }

        fun dataSuccess(): String {
            return "Verileriniz başarılı bir şekilde kaydedildi."
        }

        fun invalidProduct(): String {
            return "Lütfen barkod numarasını kontrol ediniz."
        }

        fun wrongProduct(): String {
            return "Seçilen ürün sayıma dahil edilemez."
        }

        fun invalidOperation(): String {
            return "İşlem bulunduğunuz alanda gerçekleştirilemez."
        }

        fun noCounting(): String {
            return "Envanter sorumlusu tarafından başlatılmış sayım bulunmamaktadır."
        }

        fun recount(): String {
            return "Seçilen bölgede tekrar sayım yapmak istediğinizden emin misiniz?"
        }
    }}