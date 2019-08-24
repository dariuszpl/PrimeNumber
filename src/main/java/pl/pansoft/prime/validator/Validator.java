package pl.pansoft.prime.validator;

public class Validator {

        private String fieldName;
        private String error;

        public Validator(String fieldName, String error) {
            this.fieldName = fieldName;
            this.error = error;
        }

        public String getFieldName() {
            return fieldName;
        }
        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getError() {
            return error;
        }
        public void setError(String error) {
            this.error = error;
        }
}
