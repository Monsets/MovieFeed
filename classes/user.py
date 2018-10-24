class User():
    def __init__(self, ID,encLogin,encPass,status, lastdate,MAC):
        self.ID = ID
        self.encLogin = encLogin
        self.encPass = encPass
        self.status = status
        self.lastdate = lastdate
        self.MAC = MAC