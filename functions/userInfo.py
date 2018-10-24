from classes.user import User

def getUserInfo(encLogin,encPass):
    """Function gets info about user from Data Base
            and compares with encrypted login and password"""

    userInfo = __getUserInfoFromDB(encLogin)

    if(__compareLoginAndPass(encLogin,encPass,userInfo.encLogin,userInfo.encPass) == True):
        return userInfo
    else:
        return


def __getUserInfoFromDB(encLogin):
    user = User()

    #Get's info from DB

    return user

def __compareLoginAndPass(encLogin,encPass,encLoginDB,encPassDB):

    if(encLogin == encLoginDB and encPass == encPassDB):
        return True
    else:
        return False