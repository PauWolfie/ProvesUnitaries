import data.Nif;
import data.PINcode;
import data.Password;
import services.CertificationAuthority;
import services.exceptions.*;

import java.net.ConnectException;
import java.util.Date;

public class UnifiedPlatform {
    Nif nif;
    Date date;
    /*
    ...
     */


    private static class CertificationAuthorityDoble implements CertificationAuthority{
        Nif nif;
        Date date;
        private CertificationAuthorityDoble(Nif nif,Date date){
            this.nif = nif;
            this.date = date;
        }
        @Override
        public boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException {
            if(!this.nif.equals(nif) || !this.date.equals(date)){
                throw new IncorrectValDateException("El nif i la data de validesa no corresponen");
            }
            //proporciona las credenciales al sistema Cl@ve PIN
            //solicita la emisión del PIN para completar su identificación
            return true;
        }

        @Override
        public boolean checkPIN(Nif nif, PINcode pin) throws NotValidPINException, ConnectException {
            return false;
        }

        @Override
        public byte ckeckCredent(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException, ConnectException {
            return 0;
        }
    }
}
