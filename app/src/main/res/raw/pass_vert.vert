attribute vec3 inputPosition;
attribute vec2 inputTexCoord;
attribute vec3 inputNormal;

uniform mat4 projection;
uniform mat4 modelview;
uniform mat4 normalMat;
uniform int mode;

varying vec4 forFragColor;

void main(){
    gl_Position = projection * modelview * vec4(inputPosition, 1.0);

    vec4 normal = normalMat * vec4(inputNormal, 0.0);
    forFragColor = vec4(1.0,0.0,0.0,1.0);
    if(mode == 1) forFragColor = normal;
    if(mode == 2) forFragColor = vec4(inputNormal, 1.0);
    if(mode == 3) forFragColor = gl_Position;
    if(mode == 4) forFragColor = vec4(inputPosition, 1.0);
    if(mode == 5) forFragColor = vec4(inputTexCoord, 0.0, 1.0);
}
