Create S3 Bucket
aws s3api create-bucket —bucket citi-hackathon-test-<random number> —region us-east-1

Create dynamoDB table

aws dynamodb create-table \
 —table-name dynamo-tabl-for-Citi-demo \
 —attribute-definitions \
 AttributeName=Team,AttributeType=S \
 AttributeName=YOE,AttributeType=S \
 —key-schema \
 AttributeName=Team,KeyType=HASH \
AttributeName=YOE,KeyType=RANGE \
 —provisioned-throughput \
 ReadCapacityUnits=5,WriteCapacityUnits=5 —region us-east-1

Create IAM Role for app Runner: 

cat << EOF > apprunner-role-policy.json
{
 "Version": "2012-10-17",
 "Statement": [
 {
 "Action": "sts:AssumeRole",
 "Principal": {
 "Service": "tasks.apprunner.amazonaws.com"
 },
 "Effect": "Allow",
 "Sid": ""
 }
 ]
}
EOF

aws iam create-role \
 —role-name apprunner-role \
 —assume-role-policy-document file://apprunner-role-policy.json

Attach your service permissions

aws iam attach-role-policy \
 —role-name apprunner-role \
 —policy-arn arn:aws:iam::aws:policy/AmazonDynamoDBReadOnlyAccess

aws iam attach-role-policy \
 —role-name apprunner-role \
— policy-arn arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess

