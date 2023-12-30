package ru.itis.core.domain.viewstates

sealed class PhoneSignUpState {
    object None : PhoneSignUpState()
    object InProcess : PhoneSignUpState()
    object CodeSent : PhoneSignUpState()
    object VerificationComplete : PhoneSignUpState()
    object VerificationInProcess : PhoneSignUpState()
    object Error : PhoneSignUpState()
    class InvalidCredential(val message: String?) : PhoneSignUpState()
    class VerificationFailure(val message: String?) : PhoneSignUpState()
    class TooManyRequests(val message: String?) : PhoneSignUpState()
}
