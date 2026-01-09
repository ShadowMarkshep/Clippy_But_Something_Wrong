#version 150

uniform sampler2D InSampler;
in vec2 texCoord;
out vec4 fragColor;

uniform float aberrationAmount;

void main() {
    vec2 uv = texCoord;

    vec2 redOffset = uv + aberrationAmount * vec2(0.01, 0.0);
    vec2 greenOffset = uv;
    vec2 blueOffset = uv - aberrationAmount * vec2(0.01, 0.0);

    float r = texture(InSampler, redOffset).r;
    float g = texture(InSampler, greenOffset).g;
    float b = texture(InSampler, blueOffset).b;

    fragColor = vec4(r, g, b, 1.0);
}
