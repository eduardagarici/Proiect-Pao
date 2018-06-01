package core;

public class RoomStatus {
        private String id;
        private String status1;
        private String status2="";
		
        public RoomStatus(String id, String status1, String status2) {
			super();
			this.id = id;
			this.status1 = status1;
			this.status2 = status2;
		}
        
        public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getStatus1() {
			return status1;
		}
		public void setStatus1(String status1) {
			this.status1 = status1;
		}
		
		public String getStatus2() {
			return status2;
		}
		public void setStatus2(String status2) {
			this.status2 = status2;
		}
}
