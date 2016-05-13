pushd hostur
sbt assembly
popd
pushd plugins/xml
sbt package
popd
pushd plugins/json
sbt package
popd
