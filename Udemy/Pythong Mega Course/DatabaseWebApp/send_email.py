from email.mime.text import MIMEText
import smtplib

email_sender = "aaaa@gmail.com"
password_sender = "password"

def send_email(email, height, average_height, count):
    from_email = email_sender
    from_password = password_sender
    to_email = email

    subject = "Height data"
    message = "Hey there, your height is <strong>%s</strong>. <br> Average height of all is <strong>%s</strong> and that is calculated out of <strong>%s</strong> people." % (height, average_height, count)

    msg = MIMEText(message, 'html')
    msg['Subject'] = subject
    msg['To'] = to_email
    msg['From'] = from_email

    #Standard workflow
    gmail = smtplib.SMTP('smtp.gmail.com', 587)
    gmail.ehlo()
    gmail.starttls()
    gmail.login(from_email, from_password)
    gmail.send_message(msg)
